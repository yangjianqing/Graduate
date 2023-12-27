package org.graduate.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 公司管理对象 b_company
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
public class BCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司Id */
    private Long cId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String cName;

    /** 成立时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成立时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cData;

    /** 公司类型 */
    @Excel(name = "公司类型")
    private String cType;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String cAddress;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String cSheng;


    /** 联系方式 */
    @Excel(name = "联系方式")
    private String cPhone;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal cLongitude;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal cLatitude;

    public void setcId(Long cId) 
    {
        this.cId = cId;
    }

    public Long getcId() 
    {
        return cId;
    }
    public void setcName(String cName) 
    {
        this.cName = cName;
    }

    public String getcName() 
    {
        return cName;
    }
    public void setcData(Date cData) 
    {
        this.cData = cData;
    }

    public Date getcData() 
    {
        return cData;
    }
    public void setcType(String cType) 
    {
        this.cType = cType;
    }

    public String getcSheng()
    {
        return cSheng;
    }
    public void setcSheng(String cSheng)
    {
        this.cSheng = cSheng;
    }

    public String getcType() 
    {
        return cType;
    }
    public void setcAddress(String cAddress) 
    {
        this.cAddress = cAddress;
    }

    public String getcAddress() 
    {
        return cAddress;
    }
    public void setcPhone(String cPhone) 
    {
        this.cPhone = cPhone;
    }

    public String getcPhone() 
    {
        return cPhone;
    }
    public void setcLongitude(BigDecimal cLongitude) 
    {
        this.cLongitude = cLongitude;
    }

    public BigDecimal getcLongitude() 
    {
        return cLongitude;
    }
    public void setcLatitude(BigDecimal cLatitude) 
    {
        this.cLatitude = cLatitude;
    }

    public BigDecimal getcLatitude() 
    {
        return cLatitude;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cId", getcId())
            .append("cName", getcName())
            .append("cData", getcData())
            .append("cType", getcType())
            .append("cAddress", getcAddress())
            .append("cPhone", getcPhone())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("cLongitude", getcLongitude())
            .append("cLatitude", getcLatitude())
            .append("cSheng",getcSheng())
            .toString();
    }
}
