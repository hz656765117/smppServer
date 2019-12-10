package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class OperatorVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * INTEGER(10) 必填<br>
	 * 主键id
	 */
	private Integer id;

	/**
	 * VARCHAR(255)<br>
	 * 运营商名称
	 */
	private String name;

	/**
	 * VARCHAR(255) 必填<br>
	 * 运营商ip
	 */
	private String ip;

	/**
	 * VARCHAR(255) 必填<br>
	 * 运营商port
	 */
	private String port;

	/**
	 * VARCHAR(255) 必填<br>
	 * 运营商账号
	 */
	private String systemid;

	/**
	 * VARCHAR(255) 必填<br>
	 * 运营商密码
	 */
	private String password;

	/**
	 * INTEGER(10) 必填<br>
	 * 运营商状态
	 */
	private Integer flag;

	/**
	 * VARCHAR(255)<br>
	 * 描述
	 */
	private String desc;

	/**
	 * TIMESTAMP(19)<br>
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * TIMESTAMP(19)<br>
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * VARCHAR(255) 必填<br>
	 * 通道
	 */
	private String channel;

	/**
	 * VARCHAR(255) 必填<br>
	 * 运营商senderid
	 */
	private String senderid;

	/**
	 * INTEGER(10)<br>
	 * 通道类型 0 opt  1 通知   2 营销
	 */
	private Integer type;

	/**
	 * INTEGER(10) 必填<br>
	 * 获得 主键id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * INTEGER(10) 必填<br>
	 * 设置 主键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * VARCHAR(255)<br>
	 * 获得 运营商名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * VARCHAR(255)<br>
	 * 设置 运营商名称
	 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 获得 运营商ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 设置 运营商ip
	 */
	public void setIp(String ip) {
		this.ip = ip == null ? null : ip.trim();
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 获得 运营商port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 设置 运营商port
	 */
	public void setPort(String port) {
		this.port = port == null ? null : port.trim();
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 获得 运营商账号
	 */
	public String getSystemid() {
		return systemid;
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 设置 运营商账号
	 */
	public void setSystemid(String systemid) {
		this.systemid = systemid == null ? null : systemid.trim();
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 获得 运营商密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * VARCHAR(255) 必填<br>
	 * 设置 运营商密码
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * INTEGER(10) 必填<br>
	 * 获得 运营商状态
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * INTEGER(10) 必填<br>
	 * 设置 运营商状态
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * VARCHAR(255)<br>
	 * 获得 描述
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * VARCHAR(255)<br>
	 * 设置 描述
	 */
	public void setDesc(String desc) {
		this.desc = desc == null ? null : desc.trim();
	}

	/**
	 * TIMESTAMP(19)<br>
	 * 获得 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * TIMESTAMP(19)<br>
	 * 设置 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * TIMESTAMP(19)<br>
	 * 获得 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * TIMESTAMP(19)<br>
	 * 设置 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}