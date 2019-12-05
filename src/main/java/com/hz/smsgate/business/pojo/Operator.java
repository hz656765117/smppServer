package com.hz.smsgate.business.pojo;

import java.io.Serializable;
import java.util.Date;

public class Operator implements Serializable {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append(", systemid=").append(systemid);
        sb.append(", password=").append(password);
        sb.append(", flag=").append(flag);
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
        Operator other = (Operator) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getPort() == null ? other.getPort() == null : this.getPort().equals(other.getPort()))
            && (this.getSystemid() == null ? other.getSystemid() == null : this.getSystemid().equals(other.getSystemid()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getPort() == null) ? 0 : getPort().hashCode());
        result = prime * result + ((getSystemid() == null) ? 0 : getSystemid().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}