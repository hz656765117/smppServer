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

	public MsgVo() {
	}

	public MsgVo(String msgId, String smppUser, String smppPwd, String senderId) {
		this.msgId = msgId;
		this.smppUser = smppUser;
		this.smppPwd = smppPwd;
		this.senderId = senderId;
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
}
