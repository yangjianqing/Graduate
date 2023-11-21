package org.graduate.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 学生管理对象 b_student
 * 
 * @author chuan
 * @date 2023-11-21
 */
public class B_Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生 */
    @Excel(name = "学生")
    private Long sId;

    /** 学生学号 */
    @Excel(name = "学生学号")
    private String sNumber;

    /** 学生姓名 */
    @Excel(name = "学生姓名")
    private String sName;

    /** 性别 */
    @Excel(name = "性别")
    private Long sGender;

    /** 学校 */
    @Excel(name = "学校")
    private Long schoolId;

    /** 公司 */
    @Excel(name = "公司")
    private Long compenyId;

    /** 就业状态 */
    @Excel(name = "就业状态")
    private Long cStatus;

    /** 辅导员 */
    @Excel(name = "辅导员")
    private Long teacherId;

    /** 班级 */
    @Excel(name = "班级")
    private Long classId;

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
    public void setsGender(Long sGender) 
    {
        this.sGender = sGender;
    }

    public Long getsGender() 
    {
        return sGender;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("sNumber", getsNumber())
            .append("sName", getsName())
            .append("sGender", getsGender())
            .append("schoolId", getSchoolId())
            .append("compenyId", getCompenyId())
            .append("cStatus", getcStatus())
            .append("teacherId", getTeacherId())
            .append("classId", getClassId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
