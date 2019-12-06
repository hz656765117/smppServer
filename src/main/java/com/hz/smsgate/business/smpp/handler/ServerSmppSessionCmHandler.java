package com.hz.smsgate.business.smpp.handler;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.exception.RecoverablePduException;
import com.hz.smsgate.base.smpp.exception.UnrecoverablePduException;
import com.hz.smsgate.base.smpp.pdu.*;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.PduAsyncResponse;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.utils.PduUtils;
import com.hz.smsgate.base.utils.RedisUtil;
import com.hz.smsgate.base.utils.SmppUtils;
import com.hz.smsgate.business.pojo.MsgVo;
import com.hz.smsgate.business.pojo.SmppUserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @Auther: huangzhuo
 * @Date: 2019/8/28 10:33
 * @Description:
 */
@Component
public class ServerSmppSessionCmHandler extends DefaultSmppSessionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ServerSmppSessionCmHandler.class);


	@Autowired
	public RedisUtil redisUtil;

	public static ServerSmppSessionCmHandler serverSmppSessionCmHandler;

	@PostConstruct
	public void init() {
		serverSmppSessionCmHandler = this;
		serverSmppSessionCmHandler.redisUtil = this.redisUtil;
	}


	private WeakReference<SmppSession> sessionRef;

	public ServerSmppSessionCmHandler(SmppSession session) {
		this.sessionRef = new WeakReference<>(session);
	}

	public ServerSmppSessionCmHandler() {
		super();
	}

	public ServerSmppSessionCmHandler(Logger logger) {
		super(logger);
	}

	@Override
	public String lookupResultMessage(int commandStatus) {
		return super.lookupResultMessage(commandStatus);
	}

	@Override
	public String lookupTlvTagName(short tag) {
		return super.lookupTlvTagName(tag);
	}

	@Override
	public void fireChannelUnexpectedlyClosed() {
		super.fireChannelUnexpectedlyClosed();
	}

	@Override
	public PduResponse firePduRequestReceived(PduRequest pduRequest) {
		PduResponse response = pduRequest.createResponse();


		try {
			if (pduRequest.isRequest()) {
				if (pduRequest.getCommandId() == SmppConstants.CMD_ID_SUBMIT_SM) {
					SubmitSmResp submitResp = (SubmitSmResp) response;
					SubmitSm submitSm = (SubmitSm) pduRequest;

					String msgid = SmppUtils.getMsgId();
					submitSm.setTempMsgId(msgid);


					SmppSession session = this.sessionRef.get();


					//一个账号发多个国家
					submitSm = getRealSubmitSm(submitSm, session);
					MsgVo msgVo = new MsgVo(msgid, session.getConfiguration().getSystemId(), session.getConfiguration().getPassword(), submitSm.getSourceAddress().getAddress());

					try {
						serverSmppSessionCmHandler.redisUtil.hmSet(SmppServerConstants.CM_MSGID_CACHE, msgid, msgVo);
						putSelfQueue(submitSm);
					} catch (Exception e) {
						logger.error("-----------短短信下行接收，加入队列异常。------------- ", e);
					}

					submitResp.setMessageId(msgid);
					submitResp.calculateAndSetCommandLength();
					return submitResp;

				} else if (pduRequest.getCommandId() == SmppConstants.CMD_ID_DELIVER_SM) {
					return response;
				} else if (pduRequest.getCommandId() == SmppConstants.CMD_ID_ENQUIRE_LINK) {
					return response;
				} else {
					return response;
				}
			} else {
				if (pduRequest.getCommandId() == SmppConstants.CMD_ID_SUBMIT_SM_RESP) {
					return response;
				} else {
					return response;
				}

			}
		} catch (Exception e) {
			return response;
		}
	}

	/**
	 * 父账号，替换真实的systemId和senderId
	 *
	 * @param submitSm 下行对象
	 * @return 下行对象
	 */
	public SubmitSm getRealSubmitSm(SubmitSm submitSm, SmppSession session) {
		if (session == null) {
			return submitSm;
		}

		try {
			SmppUserVo smppUserFather = PduUtils.getSmppUserByUserPwd(session.getConfiguration().getSystemId(), session.getConfiguration().getPassword());
			//如果不是父账号，不做处理
			if (smppUserFather == null) {
				//如果查不到账号，则拿绑定的账号当做systemId
				submitSm.setSystemId(session.getConfiguration().getSystemId());
				return submitSm;
			}

			Address sourceAddress = submitSm.getSourceAddress();

			List<SmppUserVo> list = smppUserFather.getList();
			if (list == null || list.size() <= 0) {
				submitSm.setSystemId(smppUserFather.getSystemid());
				sourceAddress.setAddress(smppUserFather.getSenderid());
				submitSm.setSourceAddress(sourceAddress);
				return submitSm;
			}

			String mbl = submitSm.getDestAddress().getAddress();
			//获取区号
			String areaCode = PduUtils.getAreaCode(mbl);

			String sm = new String(submitSm.getShortMessage());

			//短信类型 0 opt  1 营销
			Integer msgType = 1;
			if (StringUtils.isNotBlank(sm) && sm.toLowerCase().contains("code")) {
				msgType = 0;
			}

			String systemId = null;
			String senderId = null;
			for (SmppUserVo smppUser : list) {
				if (areaCode.equals(smppUser.getAreaCode()) && msgType.equals(smppUser.getMsgType())) {
					systemId = smppUser.getSystemid();
					senderId = smppUser.getSenderid();
					break;
				}
			}


			if (StringUtils.isNotBlank(systemId) && StringUtils.isNotBlank(senderId)) {
				logger.info("systemId({}),senderId({})  获取真实systemId({})和senderId({})成功------------- ", submitSm.getSystemId(), sourceAddress.getAddress(), systemId, senderId);
				submitSm.setSystemId(systemId);
				sourceAddress.setAddress(senderId);
				submitSm.setSourceAddress(sourceAddress);

			} else {
				logger.error("systemId({}),senderId({})  获取真实systemId和senderId 失败------------- ", submitSm.getSystemId(), sourceAddress.getAddress());
			}
		} catch (Exception e) {
			logger.error("systemId({}),senderId({})  获取真实systemId和senderId 异常------------- ", submitSm.getSystemId(), submitSm.getSourceAddress().getAddress(), e);
		}

		return submitSm;
	}




	/**
	 * 将提交过来的短信分别放到各自的队列中
	 *
	 * @param submitSm 下行短信对象
	 */
	public void putSelfQueue(SubmitSm submitSm) {
		String senderId = submitSm.getSourceAddress().getAddress();

		//营销
		if (StaticValue.CHANNEL_YX_LIST.contains(senderId)) {
			serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);
			//通知
		} else if (StaticValue.CHANNEL_TZ_LIST.contains(senderId)) {
			serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_TZ, submitSm);
			//opt  验证码
		} else if (StaticValue.CHANNEL_OPT_LIST.contains(senderId)) {
			serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_OPT, submitSm);
			//没有分类的 放到营销短信中去
		} else {
			serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);

		}

	}


	@Override
	public void fireExpectedPduResponseReceived(PduAsyncResponse pduAsyncResponse) {
		super.fireExpectedPduResponseReceived(pduAsyncResponse);
	}

	@Override
	public void fireUnexpectedPduResponseReceived(PduResponse pduResponse) {
		super.fireUnexpectedPduResponseReceived(pduResponse);
	}

	@Override
	public void fireUnrecoverablePduException(UnrecoverablePduException e) {
		super.fireUnrecoverablePduException(e);
	}

	@Override
	public void fireRecoverablePduException(RecoverablePduException e) {
		super.fireRecoverablePduException(e);
	}

	@Override
	public void fireUnknownThrowable(Throwable t) {
		super.fireUnknownThrowable(t);
	}

	@Override
	public void firePduRequestExpired(PduRequest pduRequest) {
		super.firePduRequestExpired(pduRequest);
	}

	@Override
	public boolean firePduReceived(Pdu pdu) {
		return super.firePduReceived(pdu);
	}

	@Override
	public boolean firePduDispatch(Pdu pdu) {
		return super.firePduDispatch(pdu);
	}
}
