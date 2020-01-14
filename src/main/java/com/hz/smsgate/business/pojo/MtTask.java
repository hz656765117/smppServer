package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class MtTask implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tableName;

    /**
     * INTEGER(10) 必填<br>
     * 主键id
     */
    private Integer id;

    /**
     * VARCHAR(32)<br>
     * 供应商msgid
     */
    private String spMsgId;

    /**
     * VARCHAR(32)<br>
     * 自己的msgid
     */
    private String realMsgId;

    /**
     * VARCHAR(16)<br>
     * sp账号或smpp接入账号
     */
    private String userId;

    /**
     * VARCHAR(255)<br>
     * 运营商id
     */
    private String systemId;

    /**
     * VARCHAR(16)<br>
     * 自己的通道
     */
    private String channel;

    /**
     * VARCHAR(16)<br>
     * 供应商的通道
     */
    private String senderId;

    /**
     * VARCHAR(11)<br>
     * 手机号码
     */
    private String phone;

    /**
     * VARCHAR(1000)<br>
     * 短信内容
     */
    private String message;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 发送类型（0 smpp接入   1 http）
     */
    private Integer sendType;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 发送优先级（1-9   从低到高）
     */
    private Integer sendLevel;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 发送状态（0 未提交，1 已提交）
     */
    private Integer sendFlag;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 接受状态（0  未接受，1 已接受）
     */
    private Integer receiveFlag;

    /**
     * VARCHAR(255)<br>
     * 状态报告状态
     */
    private String errorCode;

    /**
     * TIMESTAMP(19)<br>
     * 发送时间
     */
    private Date sendTime;

    /**
     * TIMESTAMP(19)<br>
     * 接收状态报告时间
     */
    private Date receiveTime;

    /**
     * VARCHAR(16)<br>
     * 任务id
     */
    private String taskId;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 长短信
     */
    private Integer longMsgSeq;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 
     */
    private Integer tpUdhi;

    /**
     * INTEGER(10) 默认值[0]<br>
     * 
     */
    private Integer tpPid;

    /**
     * VARCHAR(255)<br>
     * 区号
     */
    private String areaCode;

    /**
     * VARCHAR(255)<br>
     * 号段
     */
    private String numSeg;

    /**
     * INTEGER(10) 默认值[1]<br>
     * 长短信第几条
     */
    private Integer pkNumber;

    /**
     * INTEGER(10) 默认值[1]<br>
     * 长短信拆分条数
     */
    private Integer pkTotal;

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
     * VARCHAR(32)<br>
     * 获得 供应商msgid
     */
    public String getSpMsgId() {
        return spMsgId;
    }

    /**
     * VARCHAR(32)<br>
     * 设置 供应商msgid
     */
    public void setSpMsgId(String spMsgId) {
        this.spMsgId = spMsgId == null ? null : spMsgId.trim();
    }

    /**
     * VARCHAR(32)<br>
     * 获得 自己的msgid
     */
    public String getRealMsgId() {
        return realMsgId;
    }

    /**
     * VARCHAR(32)<br>
     * 设置 自己的msgid
     */
    public void setRealMsgId(String realMsgId) {
        this.realMsgId = realMsgId == null ? null : realMsgId.trim();
    }

    /**
     * VARCHAR(16)<br>
     * 获得 sp账号或smpp接入账号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * VARCHAR(16)<br>
     * 设置 sp账号或smpp接入账号
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * VARCHAR(255)<br>
     * 获得 运营商id
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 运营商id
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    /**
     * VARCHAR(16)<br>
     * 获得 自己的通道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * VARCHAR(16)<br>
     * 设置 自己的通道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * VARCHAR(16)<br>
     * 获得 供应商的通道
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * VARCHAR(16)<br>
     * 设置 供应商的通道
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId == null ? null : senderId.trim();
    }

    /**
     * VARCHAR(11)<br>
     * 获得 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * VARCHAR(11)<br>
     * 设置 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * VARCHAR(1000)<br>
     * 获得 短信内容
     */
    public String getMessage() {
        return message;
    }

    /**
     * VARCHAR(1000)<br>
     * 设置 短信内容
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 发送类型（0 smpp接入   1 http）
     */
    public Integer getSendType() {
        return sendType;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 发送类型（0 smpp接入   1 http）
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 发送优先级（1-9   从低到高）
     */
    public Integer getSendLevel() {
        return sendLevel;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 发送优先级（1-9   从低到高）
     */
    public void setSendLevel(Integer sendLevel) {
        this.sendLevel = sendLevel;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 发送状态（0 未提交，1 已提交）
     */
    public Integer getSendFlag() {
        return sendFlag;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 发送状态（0 未提交，1 已提交）
     */
    public void setSendFlag(Integer sendFlag) {
        this.sendFlag = sendFlag;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 接受状态（0  未接受，1 已接受）
     */
    public Integer getReceiveFlag() {
        return receiveFlag;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 接受状态（0  未接受，1 已接受）
     */
    public void setReceiveFlag(Integer receiveFlag) {
        this.receiveFlag = receiveFlag;
    }

    /**
     * VARCHAR(255)<br>
     * 获得 状态报告状态
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 状态报告状态
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode == null ? null : errorCode.trim();
    }

    /**
     * TIMESTAMP(19)<br>
     * 获得 发送时间
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 发送时间
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 获得 接收状态报告时间
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     * TIMESTAMP(19)<br>
     * 设置 接收状态报告时间
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     * VARCHAR(16)<br>
     * 获得 任务id
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * VARCHAR(16)<br>
     * 设置 任务id
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 长短信
     */
    public Integer getLongMsgSeq() {
        return longMsgSeq;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 长短信
     */
    public void setLongMsgSeq(Integer longMsgSeq) {
        this.longMsgSeq = longMsgSeq;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 
     */
    public Integer getTpUdhi() {
        return tpUdhi;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 
     */
    public void setTpUdhi(Integer tpUdhi) {
        this.tpUdhi = tpUdhi;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 获得 
     */
    public Integer getTpPid() {
        return tpPid;
    }

    /**
     * INTEGER(10) 默认值[0]<br>
     * 设置 
     */
    public void setTpPid(Integer tpPid) {
        this.tpPid = tpPid;
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
     * VARCHAR(255)<br>
     * 获得 号段
     */
    public String getNumSeg() {
        return numSeg;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 号段
     */
    public void setNumSeg(String numSeg) {
        this.numSeg = numSeg == null ? null : numSeg.trim();
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 获得 长短信第几条
     */
    public Integer getPkNumber() {
        return pkNumber;
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 设置 长短信第几条
     */
    public void setPkNumber(Integer pkNumber) {
        this.pkNumber = pkNumber;
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 获得 长短信拆分条数
     */
    public Integer getPkTotal() {
        return pkTotal;
    }

    /**
     * INTEGER(10) 默认值[1]<br>
     * 设置 长短信拆分条数
     */
    public void setPkTotal(Integer pkTotal) {
        this.pkTotal = pkTotal;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", spMsgId=").append(spMsgId);
        sb.append(", realMsgId=").append(realMsgId);
        sb.append(", userId=").append(userId);
        sb.append(", systemId=").append(systemId);
        sb.append(", channel=").append(channel);
        sb.append(", senderId=").append(senderId);
        sb.append(", phone=").append(phone);
        sb.append(", message=").append(message);
        sb.append(", sendType=").append(sendType);
        sb.append(", sendLevel=").append(sendLevel);
        sb.append(", sendFlag=").append(sendFlag);
        sb.append(", receiveFlag=").append(receiveFlag);
        sb.append(", errorCode=").append(errorCode);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", taskId=").append(taskId);
        sb.append(", longMsgSeq=").append(longMsgSeq);
        sb.append(", tpUdhi=").append(tpUdhi);
        sb.append(", tpPid=").append(tpPid);
        sb.append(", areaCode=").append(areaCode);
        sb.append(", numSeg=").append(numSeg);
        sb.append(", pkNumber=").append(pkNumber);
        sb.append(", pkTotal=").append(pkTotal);
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
        MtTask other = (MtTask) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSpMsgId() == null ? other.getSpMsgId() == null : this.getSpMsgId().equals(other.getSpMsgId()))
            && (this.getRealMsgId() == null ? other.getRealMsgId() == null : this.getRealMsgId().equals(other.getRealMsgId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getSystemId() == null ? other.getSystemId() == null : this.getSystemId().equals(other.getSystemId()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getSenderId() == null ? other.getSenderId() == null : this.getSenderId().equals(other.getSenderId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getSendType() == null ? other.getSendType() == null : this.getSendType().equals(other.getSendType()))
            && (this.getSendLevel() == null ? other.getSendLevel() == null : this.getSendLevel().equals(other.getSendLevel()))
            && (this.getSendFlag() == null ? other.getSendFlag() == null : this.getSendFlag().equals(other.getSendFlag()))
            && (this.getReceiveFlag() == null ? other.getReceiveFlag() == null : this.getReceiveFlag().equals(other.getReceiveFlag()))
            && (this.getErrorCode() == null ? other.getErrorCode() == null : this.getErrorCode().equals(other.getErrorCode()))
            && (this.getSendTime() == null ? other.getSendTime() == null : this.getSendTime().equals(other.getSendTime()))
            && (this.getReceiveTime() == null ? other.getReceiveTime() == null : this.getReceiveTime().equals(other.getReceiveTime()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getLongMsgSeq() == null ? other.getLongMsgSeq() == null : this.getLongMsgSeq().equals(other.getLongMsgSeq()))
            && (this.getTpUdhi() == null ? other.getTpUdhi() == null : this.getTpUdhi().equals(other.getTpUdhi()))
            && (this.getTpPid() == null ? other.getTpPid() == null : this.getTpPid().equals(other.getTpPid()))
            && (this.getAreaCode() == null ? other.getAreaCode() == null : this.getAreaCode().equals(other.getAreaCode()))
            && (this.getNumSeg() == null ? other.getNumSeg() == null : this.getNumSeg().equals(other.getNumSeg()))
            && (this.getPkNumber() == null ? other.getPkNumber() == null : this.getPkNumber().equals(other.getPkNumber()))
            && (this.getPkTotal() == null ? other.getPkTotal() == null : this.getPkTotal().equals(other.getPkTotal()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSpMsgId() == null) ? 0 : getSpMsgId().hashCode());
        result = prime * result + ((getRealMsgId() == null) ? 0 : getRealMsgId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getSystemId() == null) ? 0 : getSystemId().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getSenderId() == null) ? 0 : getSenderId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());
        result = prime * result + ((getSendLevel() == null) ? 0 : getSendLevel().hashCode());
        result = prime * result + ((getSendFlag() == null) ? 0 : getSendFlag().hashCode());
        result = prime * result + ((getReceiveFlag() == null) ? 0 : getReceiveFlag().hashCode());
        result = prime * result + ((getErrorCode() == null) ? 0 : getErrorCode().hashCode());
        result = prime * result + ((getSendTime() == null) ? 0 : getSendTime().hashCode());
        result = prime * result + ((getReceiveTime() == null) ? 0 : getReceiveTime().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getLongMsgSeq() == null) ? 0 : getLongMsgSeq().hashCode());
        result = prime * result + ((getTpUdhi() == null) ? 0 : getTpUdhi().hashCode());
        result = prime * result + ((getTpPid() == null) ? 0 : getTpPid().hashCode());
        result = prime * result + ((getAreaCode() == null) ? 0 : getAreaCode().hashCode());
        result = prime * result + ((getNumSeg() == null) ? 0 : getNumSeg().hashCode());
        result = prime * result + ((getPkNumber() == null) ? 0 : getPkNumber().hashCode());
        result = prime * result + ((getPkTotal() == null) ? 0 : getPkTotal().hashCode());
        return result;
    }
}