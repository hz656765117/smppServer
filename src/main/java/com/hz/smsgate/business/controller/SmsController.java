package com.hz.smsgate.business.controller;

import com.hz.smsgate.base.constants.SmppServerConstants;
import com.hz.smsgate.base.smpp.constants.SmppConstants;
import com.hz.smsgate.base.smpp.pdu.SubmitSm;
import com.hz.smsgate.base.smpp.pojo.Address;
import com.hz.smsgate.base.smpp.pojo.SessionKey;
import com.hz.smsgate.base.utils.ChangeCharset;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SmsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsController.class);
    @Autowired
    public RedisUtil redisUtil;

    @RequestMapping(value = "/sms/rsend.hts")
    public String demoTest(String command, String spid, String pwd, String das, String sm, String custid) {
        LOGGER.info("command:{},spid:{},pwd:{},das:{},sm:{},custid:{}", command, spid, pwd, das, sm, custid);
        String mtStat = "ACCEPTD";
        String mtErrCode = "0";

        sm = ChangeCharset.toStringHex(sm);

        LOGGER.info("{}-解码后的短信内容为{}",das,sm);

        String[] mbls = das.split(",");

        if(StringUtils.isBlank(command) || !"MT_REQUEST".equalsIgnoreCase(command) ){
            mtErrCode = "-13";
            mtStat = "REJECTD";
        }

        if(StringUtils.isBlank(spid) || StringUtils.isBlank(pwd)  ){
            mtErrCode = "-100";
            mtStat = "REJECTD";
        }

        if( StringUtils.isBlank(das)){
            mtErrCode = "-4";
            mtStat = "REJECTD";
        }


        if(StringUtils.isBlank(sm)){
            mtErrCode = "-5";
            mtStat = "REJECTD";
        }

        if (mbls != null && mbls.length > 100) {
            mtErrCode = "-2";
            mtStat = "REJECTD";
        }

        SmppUserVo smppUserByUserPwd = PduUtils.getSmppUserByUserPwd(spid, pwd);
        if(smppUserByUserPwd == null){
            mtErrCode = "-1";
            mtStat = "REJECTD";
        }



        String msgids = "";

        for (String mbl : mbls) {
            SubmitSm submitSm = new SubmitSm();

            try {
                submitSm.setRegisteredDelivery((byte) 1);
                submitSm.setShortMessage(sm.getBytes());
                submitSm.setSmppUser(spid);
                submitSm.setUserType(0);

                submitSm.setSourceAddress(new Address((byte) 0x03, (byte) 0x00, "000"));
                submitSm.setDestAddress(new Address((byte) 0x01, (byte) 0x01, mbl));

                String msgid = SmppUtils.getMsgId();
                msgids += msgid+",";
                submitSm.setTempMsgId(msgid);


                submitSm = getRealSubmitSm(submitSm, smppUserByUserPwd);

                setDataCoding(submitSm);

                MsgVo msgVo = new MsgVo(msgid, spid, pwd, submitSm.getSourceAddress().getAddress());


                redisUtil.hmSet(SmppServerConstants.CM_MSGID_CACHE, msgid, msgVo);
                redisUtil.hmSet(SmppServerConstants.BACK_MSGID_CACHE, msgid, msgVo);
                putSelfQueue(submitSm);
            } catch (Exception e) {

            }

        }
        String result = "command=MT_RESPONSE&spid="+spid+"&msgid="+msgids.substring(0,msgids.length()-1)+"&mtstat="+mtStat+"&status="+mtErrCode+"";
        LOGGER.info(result);
        return  result;
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
            redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);
            //通知
        } else if (SmppServerInit.CHANNEL_TZ_LIST.contains(sessionKey)) {
            redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_TZ, submitSm);
            //opt  验证码
        } else if (SmppServerInit.CHANNEL_OPT_LIST.contains(sessionKey)) {
            redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_OPT, submitSm);
            //没有分类的 放到营销短信中去
        } else {
            redisUtil.lPush(SmppServerConstants.CM_SUBMIT_SM_YX, submitSm);
        }

        redisUtil.lPush(SmppServerConstants.BACK_SUBMIT_SM, submitSm);

    }


    /**
     * 父账号，替换真实的systemId和senderId
     *
     * @param submitSm 下行对象
     * @return 下行对象
     */
    public SubmitSm getRealSubmitSm(SubmitSm submitSm, SmppUserVo smppUserFather) {
        if (smppUserFather == null) {
            return submitSm;
        }

        try {

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
                    LOGGER.error("手机号（{}），号段({})未配置到具体发送账号上,使用systemId（{}）和senderId（{}）发送", mbl, numSeg, systemId, senderId);
                }


            }

            if (StringUtils.isNotBlank(systemId) && StringUtils.isNotBlank(senderId)) {
                LOGGER.info("systemId({}),senderId({})  获取真实systemId({})和senderId({})成功------------- ", submitSm.getSystemId(), sourceAddress.getAddress(), systemId, senderId);
                submitSm.setSystemId(systemId);
                sourceAddress.setAddress(senderId);
                submitSm.setSourceAddress(sourceAddress);

            } else {
                LOGGER.error("systemId({}),senderId({})  获取真实systemId和senderId 失败------------- ", submitSm.getSystemId(), sourceAddress.getAddress());
            }
        } catch (Exception e) {
            LOGGER.error("systemId({}),senderId({})  获取真实systemId和senderId 异常------------- ", submitSm.getSystemId(), submitSm.getSourceAddress().getAddress(), e);
        }

        return submitSm;
    }


    public static SubmitSm setDataCoding(SubmitSm sm) {
        String content = new String(sm.getShortMessage());
        if (isContainChinese(content)) {
            sm.setDataCoding(SmppConstants.DATA_CODING_UCS2);
        }
        return sm;
    }

    /**
     * 判断字符串中是否包含中文
     *
     * @param str 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }


}
