package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class Channel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 主键id

     */
    private Integer id;

    /**
     * VARCHAR(255)<br>
     * 通道名称
     */
    private String name;

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
     * TIMESTAMP(19)<br>
     * 创建时间
     */
    private Date createTime;

    /**
     * TIMESTAMP(19) 必填<br>
     * 修改时间
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
     * VARCHAR(255)<br>
     * 获得 通道名称
     */
    public String getName() {
        return name;
    }

    /**
     * VARCHAR(255)<br>
     * 设置 通道名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * VARCHAR(255) 必填<br>
     * 获得 通道
     */
    public String getChannel() {
        return channel;
    }

    /**
     * VARCHAR(255) 必填<br>
     * 设置 通道
     */
    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    /**
     * VARCHAR(255) 必填<br>
     * 获得 运营商senderid
     */
    public String getSenderid() {
        return senderid;
    }

    /**
     * VARCHAR(255) 必填<br>
     * 设置 运营商senderid
     */
    public void setSenderid(String senderid) {
        this.senderid = senderid == null ? null : senderid.trim();
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
     * TIMESTAMP(19) 必填<br>
     * 获得 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * TIMESTAMP(19) 必填<br>
     * 设置 修改时间
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
        sb.append(", name=").append(name);
        sb.append(", channel=").append(channel);
        sb.append(", senderid=").append(senderid);
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
        Channel other = (Channel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getSenderid() == null ? other.getSenderid() == null : this.getSenderid().equals(other.getSenderid()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getSenderid() == null) ? 0 : getSenderid().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}