package org.graduate.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 公告管理对象 b_public_info
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public class BPublicInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告ID(key) */
    private Long nId;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String nTitle;

    /** 公告类型 */
    @Excel(name = "公告类型")
    private String nType;

    /** 公告内容 */
    @Excel(name = "公告内容")
    private String nContent;

    /** 公告状态(0 隐藏；1显示) */
    @Excel(name = "公告状态(0 隐藏；1显示)")
    private String nStatus;

    /** 创建者 */
    @Excel(name = "创建者")
    private String nPeople;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date nTime;

    /** 更新者 */
    @Excel(name = "更新者")
    private String nMender;

    /** 备注 */
    @Excel(name = "备注")
    private String nRemark;

    public void setnId(Long nId) 
    {
        this.nId = nId;
    }

    public Long getnId() 
    {
        return nId;
    }
    public void setnTitle(String nTitle) 
    {
        this.nTitle = nTitle;
    }

    public String getnTitle() 
    {
        return nTitle;
    }
    public void setnType(String nType) 
    {
        this.nType = nType;
    }

    public String getnType() 
    {
        return nType;
    }
    public void setnContent(String nContent) 
    {
        this.nContent = nContent;
    }

    public String getnContent() 
    {
        return nContent;
    }
    public void setnStatus(String nStatus) 
    {
        this.nStatus = nStatus;
    }

    public String getnStatus() 
    {
        return nStatus;
    }
    public void setnPeople(String nPeople) 
    {
        this.nPeople = nPeople;
    }

    public String getnPeople() 
    {
        return nPeople;
    }
    public void setnTime(Date nTime) 
    {
        this.nTime = nTime;
    }

    public Date getnTime() 
    {
        return nTime;
    }
    public void setnMender(String nMender) 
    {
        this.nMender = nMender;
    }

    public String getnMender() 
    {
        return nMender;
    }
    public void setnRemark(String nRemark) 
    {
        this.nRemark = nRemark;
    }

    public String getnRemark() 
    {
        return nRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("nId", getnId())
            .append("nTitle", getnTitle())
            .append("nType", getnType())
            .append("nContent", getnContent())
            .append("nStatus", getnStatus())
            .append("nPeople", getnPeople())
            .append("nTime", getnTime())
            .append("nMender", getnMender())
            .append("nRemark", getnRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
