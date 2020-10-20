package com.hz.smsgate.base.utils;

import com.cloudhopper.commons.charset.CharsetUtil;
import com.hz.smsgate.base.constants.StaticValue;
import com.hz.smsgate.base.smpp.pdu.DeliverSm;
import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.smpp.pojo.SmppSession;
import com.hz.smsgate.base.smpp.utils.DeliveryReceipt;
import com.hz.smsgate.business.listener.SmppServerInit;
import com.hz.smsgate.business.pojo.SmppUserVo;
import com.hz.smsgate.business.smpp.impl.DefaultSmppServer;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huangzhuo
 * @Date: 2019/9/6 15:10
 * @Description:
 */
@Component
public class PduUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(PduUtils.class);


    @Autowired
    public RedisUtil redisUtil;

    public static PduUtils pduUtils;

    @PostConstruct
    public void init() {
        pduUtils = this;
        pduUtils.redisUtil = this.redisUtil;
    }


    /**
     * 获取区号
     *
     * @param mbl
     * @return
     */
    public static String getAreaCode(String mbl) {
        String areaCode = "";
        if (StringUtils.isBlank(mbl)) {
            return areaCode;
        }

        if (mbl.startsWith("00")) {
            areaCode = mbl.substring(2, 4);
        } else {
            areaCode = mbl.substring(0, 2);
        }

        return areaCode;
    }

    /**
     * 获取号段
     *
     * @param mbl 手机号码
     * @return 号段
     */
    public static String getNumSeg(String mbl) {
        String numSeg = "";
        if (StringUtils.isBlank(mbl)) {
            return numSeg;
        }
        if (mbl.startsWith("00")) {
            numSeg = mbl.substring(0, 7);
        } else {
            numSeg = "00" + mbl.substring(0, 5);
        }
        return numSeg;
    }

    /**
     * 获取真实msgid
     *
     * @param deliverSm 状态报告
     * @return
     */
    public static String getMsgId(DeliverSm deliverSm) {
        String str = new String(deliverSm.getShortMessage());
        DeliveryReceipt deliveryReceipt;
        String messageId = "";
        try {
            deliveryReceipt = DeliveryReceipt.parseShortMessage(str, DateTimeZone.UTC);
            messageId = deliveryReceipt.getMessageId();
        } catch (Exception e) {
            LOGGER.error("{}-处理短信状态报告内容解析异常", Thread.currentThread().getName(), e);
            return messageId;
        }
        return messageId;
    }


    public static SmppUserVo getSmppUserByUserPwd(String smppUser, String smppPwd) {
        if (StringUtils.isBlank(smppUser) || StringUtils.isBlank(smppPwd)) {
            return null;
        }
        List<SmppUserVo> smppUser1 = SmppServerInit.SMPP_USER;
        for (SmppUserVo smppUserVo : smppUser1) {
            if (smppUser.equals(smppUserVo.getSmppUser()) && smppPwd.equals(smppUserVo.getSmppPwd())) {
                return smppUserVo;
            }

        }
        return null;
    }

    public static SmppUserVo getSmppUserByUserPwd(String smppUser, String smppPwd, String channel) {
        if (StringUtils.isBlank(smppUser) || StringUtils.isBlank(smppPwd)) {
            return null;
        }
        List<SmppUserVo> smppUser1 = SmppServerInit.SMPP_USER;
        for (SmppUserVo smppUserVo : smppUser1) {
            if (smppUser.equals(smppUserVo.getSmppUser()) && smppPwd.equals(smppUserVo.getSmppPwd()) && channel.equals(smppUserVo.getSmppChannel())) {
                return smppUserVo;
            }

        }
        return null;
    }


    public static SmppSession getServerSmppSession(String smppUser, String smppPwd) {
        SmppSession smppSession = null;
        try {
            SmppUserVo smppUserVo = getSmppUserByUserPwd(smppUser, smppPwd);

            if (DefaultSmppServer.smppSessionList == null || DefaultSmppServer.smppSessionList.size() < 1) {
                LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：({}),smppUser({}),smppPwd({}))-------", Thread.currentThread().getName(), smppUserVo.getSenderid(), smppUserVo.getSystemid(), smppUserVo.getSmppUser(), smppUserVo.getSmppPwd());
                return smppSession;
            }

            smppSession = getSmppSessionBySmppUser(smppUserVo);


            if (smppSession == null) {
                LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：({}),smppUser({}),smppPwd({}))-------", Thread.currentThread().getName(), smppUserVo.getSenderid(), smppUserVo.getSystemid(), smppUserVo.getSmppUser(), smppUserVo.getSmppPwd());
            }
        } catch (Exception e) {
            LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(smppUser({}),smppPwd({}))-------", Thread.currentThread().getName(), smppUser, smppPwd, e);
        }

        return smppSession;
    }


    public static SmppSession getServerSmppSession(String smppUser, String smppPwd, Integer port) {
        SmppSession smppSession = null;
        try {
            SmppUserVo smppUserVo = getSmppUserByUserPwd(smppUser, smppPwd);

            if (DefaultSmppServer.smppSessionList == null || DefaultSmppServer.smppSessionList.size() < 1) {
                LOGGER.error("{}-处理状态报告异常，未能获取到服务端连接(通道为：{}，systemId为：({}),smppUser({}),smppPwd({}))-------", Thread.currentThread().getName(), smppUserVo.getSenderid(), smppUserVo.getSystemid(), smppUserVo.getSmppUser(), smppUserVo.getSmppPwd());
                return smppSession;
            }

            smppSession = getSmppSessionBySmppUser(smppUserVo, port);


            if (smppSession == null) {
                return getServerSmppSession(smppUser, smppPwd);
            }
        } catch (Exception e) {
            LOGGER.error("{}-处理状态报告异常，未能匹配到服务端连接(smppUser({}),smppPwd({}))-------", Thread.currentThread().getName(), smppUser, smppPwd, e);
        }

        return smppSession;
    }


    public static int getCountByUserPwd(String smppUser, String smppPwd) {
        int count = 0;

        Iterator<SmppSession> iterator = DefaultSmppServer.smppSessionList.iterator();
        while (iterator.hasNext()) {
            try {
                SmppSession session = iterator.next();
                if (StringUtils.isBlank(smppUser) || StringUtils.isBlank(smppPwd)) {
                    LOGGER.error("客户端连接异常systemid:{},password:{}", smppUser, smppPwd);
                    continue;
                }

                if (session != null && session.getConfiguration() != null) {
                    if (smppUser.equals(session.getConfiguration().getSystemId()) && smppPwd.equals(session.getConfiguration().getPassword())) {
                        count++;
                        continue;
                    }
                } else {
//                    iterator.remove();
                    LOGGER.error("smppUser{},smppPwd{}bind时session为空或者Configuration为空", smppUser, smppPwd);
                }
            } catch (Exception e) {
                LOGGER.error("smppUser{},smppPwd{},计数异常{}", smppUser, smppPwd, e);
                continue;
            }
        }

        return count;
    }


    public static SmppSession getSmppSessionBySmppUser(SmppUserVo smppUserVo, Integer port) {
        if (smppUserVo == null) {
            return null;
        }
        SmppSession smppSession = null;
        for (SmppSession session : DefaultSmppServer.smppSessionList) {
            if (StringUtils.isBlank(session.getConfiguration().getSystemId()) || StringUtils.isBlank(session.getConfiguration().getPassword()) || port == null || port <= 0) {
                LOGGER.error("客户端连接异常systemid:{},password:{}", session.getConfiguration().getSystemId(), session.getConfiguration().getPassword());
                continue;
            }
            if (session.getConfiguration().getSystemId().equals(smppUserVo.getSmppUser()) && session.getConfiguration().getPassword().equals(smppUserVo.getSmppPwd()) && port == session.getConfiguration().getPort()) {
                smppSession = session;
                break;
            }
        }
        return smppSession;
    }


    public static SmppSession getSmppSessionBySmppUser(SmppUserVo smppUserVo) {
        if (smppUserVo == null) {
            return null;
        }
        SmppSession smppSession = null;
        for (SmppSession session : DefaultSmppServer.smppSessionList) {
            if (StringUtils.isBlank(session.getConfiguration().getSystemId()) || StringUtils.isBlank(session.getConfiguration().getPassword())) {
                LOGGER.error("客户端连接异常systemid:{},password:{}", session.getConfiguration().getSystemId(), session.getConfiguration().getPassword());
                continue;
            }
            if (session.getConfiguration().getSystemId().equals(smppUserVo.getSmppUser()) && session.getConfiguration().getPassword().equals(smppUserVo.getSmppPwd())) {
                smppSession = session;
                break;
            }
        }
        return smppSession;
    }


    public static SubmitSm encodeCmGsm(SubmitSm sm) {
        String systemId = sm.getSystemId();

        String mbl = sm.getDestAddress().getAddress();
        String areaCode = PduUtils.getAreaCode(mbl);

        //cm资源需要GSM格式编码
        if ((systemId.toUpperCase().startsWith("HP") && areaCode.equals("62")) || StaticValue.SYSTEMID_ALEX.equals(systemId)) {
            onlyEncodeGsm(sm);
        }
        return sm;
    }


    /**
     * 短信内容Gsm编码
     *
     * @param sm 下行短信对象
     * @return
     */
    public static SubmitSm onlyEncodeGsm(SubmitSm sm) {
        if (sm.getDataCoding() != 0) {
            return sm;
        }

        byte[] shortMessage = sm.getShortMessage();
        String content = new String(shortMessage);
        LOGGER.info("短信的内容为{},下行号码为{}，通道为{},datacoding为{}", content, sm.getDestAddress().getAddress(), sm.getSourceAddress().getAddress(), sm.getDataCoding());
        try {
            byte[] textBytes = CharsetUtil.encode(content, CharsetUtil.CHARSET_GSM);
            sm.setShortMessage(textBytes);
        } catch (Exception e) {
            LOGGER.error("短信内容编码异常", e);
        }
        LOGGER.info("短信编码后的内容为{},下行号码为{}，通道为{},datacoding为{}", new String(sm.getShortMessage()), sm.getDestAddress().getAddress(), sm.getSourceAddress().getAddress(), sm.getDataCoding());

        sm.calculateAndSetCommandLength();
        return sm;
    }


}
