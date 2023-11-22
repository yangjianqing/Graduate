package org.graduate.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 学校管理对象 b_school
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public class BSchool extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学校ID(key) */
    private Long sId;

    /** 学校名称 */
    @Excel(name = "学校名称")
    private String sName;

    /** 学校Logo */
    @Excel(name = "学校Logo")
    private String sLogo;

    /** 学校经度 */
    @Excel(name = "学校经度")
    private BigDecimal sLongitude;

    /** 学校纬度 */
    @Excel(name = "学校纬度")
    private BigDecimal sLatitude;

    /** 学校官网 */
    @Excel(name = "学校官网")
    private String sWebsite;

    /** 学校校长 */
    @Excel(name = "学校校长")
    private String sRector;

    /** 学校联系方式 */
    @Excel(name = "学校联系方式")
    private Long sConnection;

    /** 学校邮箱 */
    @Excel(name = "学校邮箱")
    private String sMailbox;

    /** 学校备注 */
    @Excel(name = "学校备注")
    private String sRemark;

    public void setsId(Long sId) 
    {
        this.sId = sId;
    }

    public Long getsId() 
    {
        return sId;
    }
    public void setsName(String sName) 
    {
        this.sName = sName;
    }

    public String getsName() 
    {
        return sName;
    }
    public void setsLogo(String sLogo) 
    {
        this.sLogo = sLogo;
    }

    public String getsLogo() 
    {
        return sLogo;
    }
    public void setsLongitude(BigDecimal sLongitude) 
    {
        this.sLongitude = sLongitude;
    }

    public BigDecimal getsLongitude() 
    {
        return sLongitude;
    }
    public void setsLatitude(BigDecimal sLatitude) 
    {
        this.sLatitude = sLatitude;
    }

    public BigDecimal getsLatitude() 
    {
        return sLatitude;
    }
    public void setsWebsite(String sWebsite) 
    {
        this.sWebsite = sWebsite;
    }

    public String getsWebsite() 
    {
        return sWebsite;
    }
    public void setsRector(String sRector) 
    {
        this.sRector = sRector;
    }

    public String getsRector() 
    {
        return sRector;
    }
    public void setsConnection(Long sConnection) 
    {
        this.sConnection = sConnection;
    }

    public Long getsConnection() 
    {
        return sConnection;
    }
    public void setsMailbox(String sMailbox) 
    {
        this.sMailbox = sMailbox;
    }

    public String getsMailbox() 
    {
        return sMailbox;
    }
    public void setsRemark(String sRemark) 
    {
        this.sRemark = sRemark;
    }

    public String getsRemark() 
    {
        return sRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sId", getsId())
            .append("sName", getsName())
            .append("sLogo", getsLogo())
            .append("sLongitude", getsLongitude())
            .append("sLatitude", getsLatitude())
            .append("sWebsite", getsWebsite())
            .append("sRector", getsRector())
            .append("sConnection", getsConnection())
            .append("sMailbox", getsMailbox())
            .append("sRemark", getsRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
