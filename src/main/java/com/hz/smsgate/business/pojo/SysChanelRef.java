package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysChanelRef implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * INTEGER(10) 必填<br>
     * 主键id
     */
    private Integer id;

    /**
     * INTEGER(10)<br>
     * 运营商主键id
     */
    private Integer sysId;

    /**
     * INTEGER(10)<br>
     * 通道主键id
     */
    private Integer channelId;

    /**
     * TIMESTAMP(19)<br>
     * 创建时间
     */
    private Date createTime;

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
     * INTEGER(10)<br>
     * 获得 运营商主键id
     */
    public Integer getSysId() {
        return sysId;
    }

    /**
     * INTEGER(10)<br>
     * 设置 运营商主键id
     */
    public void setSysId(Integer sysId) {
        this.sysId = sysId;
    }

    /**
     * INTEGER(10)<br>
     * 获得 通道主键id
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * INTEGER(10)<br>
     * 设置 通道主键id
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sysId=").append(sysId);
        sb.append(", channelId=").append(channelId);
        sb.append(", createTime=").append(createTime);
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
        SysChanelRef other = (SysChanelRef) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSysId() == null ? other.getSysId() == null : this.getSysId().equals(other.getSysId()))
            && (this.getChannelId() == null ? other.getChannelId() == null : this.getChannelId().equals(other.getChannelId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSysId() == null) ? 0 : getSysId().hashCode());
        result = prime * result + ((getChannelId() == null) ? 0 : getChannelId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}