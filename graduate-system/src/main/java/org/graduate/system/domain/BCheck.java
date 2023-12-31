package org.graduate.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 签到对象 b_check
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public class BCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Id */
    private Long ckId;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    /** 班级名称 */
    private String cName;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
    //学生Id
    private Long stuId;
    /** 公司名称 */
    private String comName;


    /** 姓名 */
    @Excel(name = "姓名")
    private String ckName;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ckTime;

    /** 地址 */
    @Excel(name = "地址")
    private String ckAddress;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal ckLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal ckLatitude;

    /** 班级 */
    @Excel(name = "班级")
    private Long bClass;

    /** 类型 */
    @Excel(name = "类型")
    private String ckTpye;

    /** 公司Id */
    @Excel(name = "公司Id")
    private Long cId;

    private String phone;

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCkId(Long ckId)
    {
        this.ckId = ckId;
    }

    public Long getCkId() 
    {
        return ckId;
    }
    public void setCkName(String ckName) 
    {
        this.ckName = ckName;
    }

    public String getCkName() 
    {
        return ckName;
    }
    public void setCkTime(Date ckTime) 
    {
        this.ckTime = ckTime;
    }

    public Date getCkTime() 
    {
        return ckTime;
    }
    public void setCkAddress(String ckAddress) 
    {
        this.ckAddress = ckAddress;
    }

    public String getCkAddress() 
    {
        return ckAddress;
    }
    public void setCkLongitude(BigDecimal ckLongitude) 
    {
        this.ckLongitude = ckLongitude;
    }

    public BigDecimal getCkLongitude() 
    {
        return ckLongitude;
    }
    public void setCkLatitude(BigDecimal ckLatitude) 
    {
        this.ckLatitude = ckLatitude;
    }

    public BigDecimal getCkLatitude() 
    {
        return ckLatitude;
    }
    public void setbClass(Long bClass) 
    {
        this.bClass = bClass;
    }

    public Long getbClass() 
    {
        return bClass;
    }
    public void setCkTpye(String ckTpye) 
    {
        this.ckTpye = ckTpye;
    }

    public String getCkTpye() 
    {
        return ckTpye;
    }
    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ckId", getCkId())
            .append("ckName", getCkName())
            .append("ckTime", getCkTime())
            .append("ckAddress", getCkAddress())
            .append("ckLongitude", getCkLongitude())
            .append("ckLatitude", getCkLatitude())
            .append("bClass", getbClass())
            .append("ckTpye", getCkTpye())
            .append("cId", getcId())
            .toString();
    }
}
