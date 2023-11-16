package org.graduate.active.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 活动信息对象 y_activity
 *
 * @author yang
 * @date 2023-04-11
 */
public class YActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 费用 */
    @Excel(name = "费用")
    private BigDecimal activityCost;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 地址 */
    @Excel(name = "地址")
    private String activityLocation;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 活动介绍 */
    @Excel(name = "活动介绍")
    private String activityIntroduce;

    /** 注意事项 */
    @Excel(name = "注意事项")
    private String activityNotice;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatTime;

    /** 图片 */
    @Excel(name = "图片")
    private String imgurl;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setActivityCost(BigDecimal activityCost)
    {
        this.activityCost = activityCost;
    }

    public BigDecimal getActivityCost()
    {
        return activityCost;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setActivityLocation(String activityLocation)
    {
        this.activityLocation = activityLocation;
    }

    public String getActivityLocation()
    {
        return activityLocation;
    }
    public void setActivityName(String activityName)
    {
        this.activityName = activityName;
    }

    public String getActivityName()
    {
        return activityName;
    }
    public void setActivityIntroduce(String activityIntroduce)
    {
        this.activityIntroduce = activityIntroduce;
    }

    public String getActivityIntroduce()
    {
        return activityIntroduce;
    }
    public void setActivityNotice(String activityNotice)
    {
        this.activityNotice = activityNotice;
    }

    public String getActivityNotice()
    {
        return activityNotice;
    }
    public void setCreatTime(Date creatTime)
    {
        this.creatTime = creatTime;
    }

    public Date getCreatTime()
    {
        return creatTime;
    }
    public void setImgurl(String imgurl)
    {
        this.imgurl = imgurl;
    }

    public String getImgurl()
    {
        return imgurl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("activityCost", getActivityCost())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("activityLocation", getActivityLocation())
                .append("activityName", getActivityName())
                .append("activityIntroduce", getActivityIntroduce())
                .append("activityNotice", getActivityNotice())
                .append("creatTime", getCreatTime())
                .append("imgurl", getImgurl())
                .toString();
    }
}
