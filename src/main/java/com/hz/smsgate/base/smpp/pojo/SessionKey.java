package com.hz.smsgate.base.smpp.pojo;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Auther: huangzhuo
 * @Date: 2019/11/18 10:15
 * @Description:
 */
public class SessionKey implements Serializable {
	public String systemId;
	public String senderId;


	public SessionKey(String systemId, String senderId) {
		this.systemId = systemId;
		this.senderId = senderId;
	}

	public SessionKey() {
	}

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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SessionKey that = (SessionKey) o;
		return systemId.equals(that.systemId) &&
				senderId.equals(that.senderId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(systemId, senderId);
	}
}
