package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class SmppUser implements Serializable {
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
     * VARCHAR(255)<br>
     * 提供出去的账号
     */
    private String systemid;

    /**
     * VARCHAR(255) 必填<br>
     * 提供出去的密码
     */
    private String password;

    /**
     * VARCHAR(255)<br>
     * 区号
     */
    private String areaCode;

    /**
     * INTEGER(10)<br>
     * 短信类型  0：opt  1：营销
     */
    private Integer msgType;

    /**
     * VARCHAR(255)<br>
     * 运营商通道绑定表主键id
     */
    private String sysChannelId;

    /**
     * INTEGER(10)<br>
     * 状态标识 0：启用  0：禁用
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
     * 获得 区号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 区号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * INTEGER(10)<br>
     * 获得 短信类型  0：opt  1：营销
     */
    public Integer getMsgType() {
        return msgType;
    }

    /**
     * INTEGER(10)<br>
     * 设置 短信类型  0：opt  1：营销
     */
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
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
     * 获得 状态标识 0：启用  0：禁用
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * INTEGER(10)<br>
     * 设置 状态标识 0：启用  0：禁用
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spUser=").append(spUser);
        sb.append(", spPwd=").append(spPwd);
        sb.append(", systemid=").append(systemid);
        sb.append(", password=").append(password);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", msgType=").append(msgType);
        sb.append(", sysChannelId=").append(sysChannelId);
        sb.append(", flag=").append(flag);
        sb.append(", userIds=").append(userIds);
        sb.append(", desc=").append(desc);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SmppUser other = (SmppUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpUser() == null ? other.getSpUser() == null : this.getSpUser().equals(other.getSpUser()))
            && (this.getSpPwd() == null ? other.getSpPwd() == null : this.getSpPwd().equals(other.getSpPwd()))
            && (this.getSystemid() == null ? other.getSystemid() == null : this.getSystemid().equals(other.getSystemid()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getMsgType() == null ? other.getMsgType() == null : this.getMsgType().equals(other.getMsgType()))
            && (this.getSysChannelId() == null ? other.getSysChannelId() == null : this.getSysChannelId().equals(other.getSysChannelId()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getUserIds() == null ? other.getUserIds() == null : this.getUserIds().equals(other.getUserIds()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpUser() == null) ? 0 : getSpUser().hashCode());
        result = prime * result + ((getSpPwd() == null) ? 0 : getSpPwd().hashCode());
        result = prime * result + ((getSystemid() == null) ? 0 : getSystemid().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getMsgType() == null) ? 0 : getMsgType().hashCode());
        result = prime * result + ((getSysChannelId() == null) ? 0 : getSysChannelId().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getUserIds() == null) ? 0 : getUserIds().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}