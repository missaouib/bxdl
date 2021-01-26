package com.hzcf.pojo.basic;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>员工工号规则pojo</p>
 *
 * @author kongqingbao
 */
public class EmployeeCodeRulePojo implements Serializable {

    private static final long serialVersionUID = 4474274517842400600L;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 序号/编号
     */
    private String code;

    /**
     * 关联id
     */
    private String mapId;

    /**
     * 关联名称
     */
    private String mapName;

    /**
     * 关联类型：1:地区(省id) 2:销售机构(orgId)
     */
    private Byte type;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}

        if (o == null || getClass() != o.getClass()) {return false;}

        EmployeeCodeRulePojo that = (EmployeeCodeRulePojo) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(code, that.code)
                .append(mapId, that.mapId)
                .append(mapName, that.mapName)
                .append(type, that.type)
                .append(createdTime, that.createdTime)
                .append(updateTime, that.updateTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(code)
                .append(mapId)
                .append(mapName)
                .append(type)
                .append(createdTime)
                .append(updateTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "EmployeeCodeRulePojo{" +
                "id=" + id +
                ", code=" + code +
                ", mapId=" + mapId +
                ", mapName='" + mapName + '\'' +
                ", type=" + type +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
