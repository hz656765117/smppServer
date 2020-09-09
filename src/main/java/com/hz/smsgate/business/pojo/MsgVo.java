package com.hz.smsgate.business.pojo;

import java.io.Serializable;

/**
 * @Auther: huangzhuo
 * @Date: 2019/12/6 09:21
 * @Description:
 */
public class MsgVo implements Serializable {
    public String msgId;

    public String smppUser;

    public String smppPwd;

    public String senderId;

    public Integer sendSize;

    public long sendTime;

    public Integer port;

    public MsgVo() {
    }

    public MsgVo(String msgId, String smppUser, String smppPwd, String senderId) {
        this.msgId = msgId;
        this.smppUser = smppUser;
        this.smppPwd = smppPwd;
        this.senderId = senderId;
        this.sendSize = 1;
        this.sendTime = System.currentTimeMillis();
        this.port = 0;
    }

    public MsgVo(String msgId, String smppUser, String smppPwd, String senderId, Integer port) {
        this.msgId = msgId;
        this.smppUser = smppUser;
        this.smppPwd = smppPwd;
        this.senderId = senderId;
        this.sendSize = 1;
        this.sendTime = System.currentTimeMillis();
        this.port = port;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getSmppUser() {
        return smppUser;
    }

    public void setSmppUser(String smppUser) {
        this.smppUser = smppUser;
    }

    public String getSmppPwd() {
        return smppPwd;
    }

    public void setSmppPwd(String smppPwd) {
        this.smppPwd = smppPwd;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }


    public Integer getSendSize() {
        return sendSize;
    }

    public void setSendSize(Integer sendSize) {
        this.sendSize = sendSize;
    }

    public long getSendTime() {
        return sendTime;
    }

    public void setSendTime(long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
