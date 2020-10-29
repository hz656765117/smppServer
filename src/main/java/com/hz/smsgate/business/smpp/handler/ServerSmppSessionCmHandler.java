package com.hz.smsgate.business.smpp.handler;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.exception.RecoverablePduException;
import com.hz.smsgate.base.smpp.exception.UnrecoverablePduException;
import com.hz.smsgate.base.smpp.pdu.*;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.PduAsyncResponse;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.utils.PduUtils;
import com.hz.smsgate.base.utils.RedisUtil;
import com.hz.smsgate.base.utils.SmppUtils;
import com.hz.smsgate.business.listener.SmppServerInit;
import com.hz.smsgate.business.pojo.MsgVo;
import com.hz.smsgate.business.pojo.SmppUserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
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
        long beginTime = System.currentTimeMillis();

        try {
            if (pduRequest.isRequest()) {
                if (pduRequest.getCommandId() == SmppConstants.CMD_ID_SUBMIT_SM) {
                    SubmitSmResp submitResp = (SubmitSmResp) response;
                    SubmitSm submitSm = (SubmitSm) pduRequest;
                    submitSm.setChannel(submitSm.getSourceAddress().getAddress());
                    String msgid = SmppUtils.getMsgId();
                    submitSm.setTempMsgId(msgid);


                    SmppSession session = this.sessionRef.get();
                    submitSm.setSmppUser(session.getConfiguration().getSystemId());
                    submitSm.setUserType(0);

                    //一个账号发多个国家
                    submitSm = getRealSubmitSm(submitSm, session);

                    if (StringUtils.isBlank(submitSm.getSystemId())) {
                        logger.error("systemId({}),password({}),senderId({})  获取smpp账号失败，该短信（mbl：{}，content：{}）不下发------------- ", session.getConfiguration().getSystemId(), session.getConfiguration().getPassword(), submitSm.getSourceAddress().getAddress(), submitSm.getDestAddress().getAddress(), new String(submitSm.getShortMessage()));
                        submitResp.setMessageId(msgid);
                        submitResp.calculateAndSetCommandLength();
                        return submitResp;
                    }

                    MsgVo msgVo = new MsgVo(msgid, session.getConfiguration().getSystemId(), session.getConfiguration().getPassword(), submitSm.getSourceAddress().getAddress(), session.getConfiguration().getPort());

                    try {
                        serverSmppSessionCmHandler.redisUtil.hmSet(SmppServerConstants.CM_MSGID_CACHE, msgid, msgVo);
                        serverSmppSessionCmHandler.redisUtil.hmSet(SmppServerConstants.BACK_MSGID_CACHE, msgid, msgVo);
                        putSelfQueue(submitSm);


                    } catch (Exception e) {
                        logger.error("-----------短短信下行接收，加入队列异常。------------- ", e);
                    }

                    submitResp.setMessageId(msgid);
                    submitResp.calculateAndSetCommandLength();

                    long endTime = System.currentTimeMillis();
                    logger.info("此次请求 systemId({})，短信（mbl：{}，content：{}），耗时{}", session.getConfiguration().getSystemId(), submitSm.getDestAddress().getAddress(), new String(submitSm.getShortMessage()), endTime - beginTime);
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

            Address sourceAddress = submitSm.getSourceAddress();

            SmppUserVo smppUserFather;
            if (StringUtils.isNotBlank(sourceAddress.getAddress())) {
                smppUserFather = PduUtils.getSmppUserByUserPwd(session.getConfiguration().getSystemId(), session.getConfiguration().getPassword(), sourceAddress.getAddress());
            } else {
                smppUserFather = PduUtils.getSmppUserByUserPwd(session.getConfiguration().getSystemId(), session.getConfiguration().getPassword());
            }


            //如果查不到账号，不发送
            if (smppUserFather == null) {
                return submitSm;
            }


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

            List<SmppUserVo> areaList = new LinkedList<>();

            for (SmppUserVo smppUser : list) {
                if (areaCode.equals(smppUser.getAreaCode())) {
                    areaList.add(smppUser);
                }
            }


            if (areaList == null || areaList.size() <= 0) {
                return submitSm;
            }

            if (areaList.size() == 1) {
                systemId = areaList.get(0).getSystemid();
                senderId = areaList.get(0).getSenderid();
            } else {
                //如果同一个国家配置了两个国家，则根据号段匹配发送
                String numSeg = PduUtils.getNumSeg(mbl);

                for (SmppUserVo smppUser : areaList) {
                    if (StringUtils.isNotBlank(smppUser.getNumSegment()) && smppUser.getNumSegment().contains(numSeg)) {
                        systemId = smppUser.getSystemid();
                        senderId = smppUser.getSenderid();
                        break;
                    }
                }

                //如果号段都没匹配上，拿第一个账号发送
                if (StringUtils.isBlank(systemId) || StringUtils.isBlank(senderId)) {
                    systemId = areaList.get(0).getSystemid();
                    senderId = areaList.get(0).getSenderid();
                    logger.error("手机号（{}），号段({})未配置到具体发送账号上,使用systemId（{}）和senderId（{}）发送", mbl, numSeg, systemId, senderId);
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
        SessionKey sessionKey = new SessionKey(submitSm.getSystemId(), senderId);

        //营销
        if (SmppServerInit.CHANNEL_YX_LIST.contains(sessionKey)) {
            serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);
            //通知
        } else if (SmppServerInit.CHANNEL_TZ_LIST.contains(sessionKey)) {
            serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_TZ, submitSm);
            //opt  验证码
        } else if (SmppServerInit.CHANNEL_OPT_LIST.contains(sessionKey)) {
            serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_OPT, submitSm);
            //没有分类的 放到营销短信中去
        } else {
            serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);
        }

        serverSmppSessionCmHandler.redisUtil.lPush(SmppServerConstants.BACK_SUBMIT_SM, submitSm);

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
