package org.graduate.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 教师信息对象 b_teacher
 * 
 * @author ruoyi
 * @date 2023-11-21
 */
public class BTeacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教师id(key) */
    private Long tchrId;

    /** 教师编号 */
    @Excel(name = "教师编号")
    private Long tchrNum;

    /** 所属院校(fkey) */
    @Excel(name = "所属院校(fkey)")
    private String schoolName;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String tchrName;

    /** 教师性别 */
    @Excel(name = "教师性别")
    private String tchrGender;

    /** 教室年龄 */
    @Excel(name = "教室年龄")
    private Long tchrAge;

    /** 教师照片 */
    @Excel(name = "教师照片")
    private String tchrImg;

    /** 教师联系方式 */
    @Excel(name = "教师联系方式")
    private Long tchrPhone;

    /** 教师状态 */
    @Excel(name = "教师状态")
    private String tchrStatus;

    public void setTchrId(Long tchrId) 
    {
        this.tchrId = tchrId;
    }

    public Long getTchrId() 
    {
        return tchrId;
    }
    public void setTchrNum(Long tchrNum) 
    {
        this.tchrNum = tchrNum;
    }

    public Long getTchrNum() 
    {
        return tchrNum;
    }
    public void setSchoolName(String schoolName) 
    {
        this.schoolName = schoolName;
    }

    public String getSchoolName() 
    {
        return schoolName;
    }
    public void setTchrName(String tchrName) 
    {
        this.tchrName = tchrName;
    }

    public String getTchrName() 
    {
        return tchrName;
    }
    public void setTchrGender(String tchrGender) 
    {
        this.tchrGender = tchrGender;
    }

    public String getTchrGender() 
    {
        return tchrGender;
    }
    public void setTchrAge(Long tchrAge) 
    {
        this.tchrAge = tchrAge;
    }

    public Long getTchrAge() 
    {
        return tchrAge;
    }
    public void setTchrImg(String tchrImg) 
    {
        this.tchrImg = tchrImg;
    }

    public String getTchrImg() 
    {
        return tchrImg;
    }
    public void setTchrPhone(Long tchrPhone) 
    {
        this.tchrPhone = tchrPhone;
    }

    public Long getTchrPhone() 
    {
        return tchrPhone;
    }
    public void setTchrStatus(String tchrStatus) 
    {
        this.tchrStatus = tchrStatus;
    }

    public String getTchrStatus() 
    {
        return tchrStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tchrId", getTchrId())
            .append("tchrNum", getTchrNum())
            .append("schoolName", getSchoolName())
            .append("tchrName", getTchrName())
            .append("tchrGender", getTchrGender())
            .append("tchrAge", getTchrAge())
            .append("tchrImg", getTchrImg())
            .append("tchrPhone", getTchrPhone())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("tchrStatus", getTchrStatus())
            .toString();
    }
}
