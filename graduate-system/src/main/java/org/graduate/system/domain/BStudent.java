package org.graduate.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 学生对象 b_student
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public class BStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生id(key) */
    private Long sId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String sNumber;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sName;

    /** 学校id(fkey) */
    @Excel(name = "学校id(fkey)")
    private Long schoolId;

    /** 公司id(fkey) */
    @Excel(name = "公司id(fkey)")
    private Long compenyId;

    /** 就业状态(0,1) */
    @Excel(name = "就业状态(0,1)")
    private Long cStatus;

    /** 辅导员id(fkey) */
    @Excel(name = "辅导员id(fkey)")
    private Long teacherId;

    /** 班级id(fkey) */
    @Excel(name = "班级id(fkey)")
    private Long classId;

    /** 虚拟删除(0:未删除，1:删除) */
    @Excel(name = "虚拟删除(0:未删除，1:删除)")
    private Long sIsdelete;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setsNumber(String sNumber) 
    {
        this.sNumber = sNumber;
    }

    public String getsNumber() 
    {
        return sNumber;
    }
    public void setsName(String sName) 
    {
        this.sName = sName;
    }

    public String getsName() 
    {
        return sName;
    }
    public void setSchoolId(Long schoolId) 
    {
        this.schoolId = schoolId;
    }

    public Long getSchoolId() 
    {
        return schoolId;
    }
    public void setCompenyId(Long compenyId) 
    {
        this.compenyId = compenyId;
    }

    public Long getCompenyId() 
    {
        return compenyId;
    }
    public void setcStatus(Long cStatus) 
    {
        this.cStatus = cStatus;
    }

    public Long getcStatus() 
    {
        return cStatus;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setClassId(Long classId) 
    {
        this.classId = classId;
    }

    public Long getClassId() 
    {
        return classId;
    }
    public void setsIsdelete(Long sIsdelete) 
    {
        this.sIsdelete = sIsdelete;
    }

    public Long getsIsdelete() 
    {
        return sIsdelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("sNumber", getsNumber())
            .append("sName", getsName())
            .append("schoolId", getSchoolId())
            .append("compenyId", getCompenyId())
            .append("cStatus", getcStatus())
            .append("teacherId", getTeacherId())
            .append("classId", getClassId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("sIsdelete", getsIsdelete())
            .toString();
    }
}
