package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SmppUserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 主键id
     */
    private Integer id;

    /**
     * VARCHAR(255) 必填<br>
     * sp账号
     */
    private String spUser;

    /**
     * VARCHAR(255) 必填<br>
     * sp密码
     */
    private String spPwd;


    /**
     * VARCHAR(255) 必填<br>
     * smpp 接入账号
     */
    private String smppUser;

    /**
     * VARCHAR(255) 必填<br>
     * smpp 接入密码
     */
    private String smppPwd;


    /**
     * VARCHAR(255)<br>
     *  运营商账号
     */
    private String systemid;


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
     * 区号
     */
    private String areaCode;


    /**
     * 短信类型 0 opt短信  1 营销短信
     */
    private Integer msgType;

    /**
     * VARCHAR(255) 必填<br>
     * 提供出去的密码
     */
    private String password;

    /**
     * VARCHAR(255)<br>
     * 运营商通道绑定表主键id
     */
    private String sysChannelId;

    /**
     * INTEGER(10)<br>
     * 
     */
    private Integer flag;

    /**
     * VARCHAR(255)<br>
     * 子账号id
     */
    private String userIds;

    /**
     * VARCHAR(255)<br>
     * 描述
     */
    private String desc;

    /**
     * TIMESTAMP(19)<br>
     * 修改时间
     */
    private Date createTime;

    /**
     * TIMESTAMP(19)<br>
     * 创建时间
     */
    private Date updateTime;


    List<SmppUserVo> list;

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
     * VARCHAR(255) 必填<br>
     * 获得 sp账号
     */
    public String getSpUser() {
        return spUser;
    }

    /**
     * VARCHAR(255) 必填<br>
     * 设置 sp账号
     */
    public void setSpUser(String spUser) {
        this.spUser = spUser == null ? null : spUser.trim();
    }

    /**
     * VARCHAR(255) 必填<br>
     * 获得 sp密码
     */
    public String getSpPwd() {
        return spPwd;
    }

    /**
     * VARCHAR(255) 必填<br>
     * 设置 sp密码
     */
    public void setSpPwd(String spPwd) {
        this.spPwd = spPwd == null ? null : spPwd.trim();
    }

    /**
     * VARCHAR(255)<br>
     * 获得 提供出去的账号
     */
    public String getSystemid() {
        return systemid;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 提供出去的账号
     */
    public void setSystemid(String systemid) {
        this.systemid = systemid == null ? null : systemid.trim();
    }

    /**
     * VARCHAR(255) 必填<br>
     * 获得 提供出去的密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * VARCHAR(255) 必填<br>
     * 设置 提供出去的密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * VARCHAR(255)<br>
     * 获得 运营商通道绑定表主键id
     */
    public String getSysChannelId() {
        return sysChannelId;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 运营商通道绑定表主键id
     */
    public void setSysChannelId(String sysChannelId) {
        this.sysChannelId = sysChannelId == null ? null : sysChannelId.trim();
    }

    /**
     * INTEGER(10)<br>
     * 获得 
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * INTEGER(10)<br>
     * 设置 
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * VARCHAR(255)<br>
     * 获得 子账号id
     */
    public String getUserIds() {
        return userIds;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 子账号id
     */
    public void setUserIds(String userIds) {
        this.userIds = userIds == null ? null : userIds.trim();
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
     * 获得 修改时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 修改时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 获得 创建时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 创建时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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


    public List<SmppUserVo> getList() {
        return list;
    }

    public void setList(List<SmppUserVo> list) {
        this.list = list;
    }


    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
}