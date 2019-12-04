package com.hz.smsgate.base.smpp.pojo;


/**
 * @Auther: huangzhuo
 * @Date: 2019/11/26 15:29
 * @Description:
 */
public class ClientKey {
	public String systemId;
	public String senderId;
	public String password;
	public String spId;
	public String spPwd;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}


}
