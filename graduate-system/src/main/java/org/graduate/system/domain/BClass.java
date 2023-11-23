package org.graduate.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 班级管理对象 b_class
 * 
 * @author chuan
 * @date 2023-11-23
 */
public class BClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 班级id */
    @Excel(name = "班级id")
    private Long cId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private String cNumber;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String cName;

    /** 辅导员 */
    @Excel(name = "辅导员")
    private Long teacherId;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String cPhone;

    /** 班级人数 */
    @Excel(name = "班级人数")
    private Long cCount;

    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setcNumber(String cNumber) 
    {
        this.cNumber = cNumber;
    }

    public String getcNumber() 
    {
        return cNumber;
    }
    public void setcName(String cName) 
    {
        this.cName = cName;
    }

    public String getcName() 
    {
        return cName;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setcPhone(String cPhone) 
    {
        this.cPhone = cPhone;
    }

    public String getcPhone() 
    {
        return cPhone;
    }
    public void setcCount(Long cCount) 
    {
        this.cCount = cCount;
    }

    public Long getcCount() 
    {
        return cCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cId", getcId())
            .append("cNumber", getcNumber())
            .append("cName", getcName())
            .append("teacherId", getTeacherId())
            .append("cPhone", getcPhone())
            .append("cCount", getcCount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
