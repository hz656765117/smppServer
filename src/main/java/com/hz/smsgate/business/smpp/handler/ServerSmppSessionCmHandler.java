package com.hz.smsgate.business.smpp.handler;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.exception.RecoverablePduException;
import com.hz.smsgate.base.smpp.exception.UnrecoverablePduException;
import com.hz.smsgate.base.smpp.pdu.*;
import com.hz.smsgate.base.smpp.pojo.PduAsyncResponse;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.utils.RedisUtil;
import com.hz.smsgate.base.utils.SmppUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.ref.WeakReference;

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

					String systemId = "";
					SmppSession session = this.sessionRef.get();
					if (session != null) {
						systemId = session.getConfiguration().getSystemId();
						submitSm.setSystemId(systemId);
					}

					logger.info("当前短信的systemid为:{},msgid为:{},", systemId, msgid);
					try {
						serverSmppSessionCmHandler.redisUtil.hmSet(SmppServerConstants.CM_MSGID_CACHE, msgid, msgid);
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
