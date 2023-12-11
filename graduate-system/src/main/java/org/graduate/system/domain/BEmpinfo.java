package org.graduate.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.graduate.common.annotation.Excel;
import org.graduate.common.core.domain.BaseEntity;

/**
 * 就业信息发布对象 b_empinfo
 *
 * @author ruoyi
 * @date 2023-11-21
 */
public class BEmpinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 就业信息id(key)
     */
    private Long empId;

    /**
     * 公司名称(fkey)
     */
    @Excel(name = "公司名称(fkey)")
    private String cName;

    /**
     * 就业岗位
     */
    @Excel(name = "就业岗位")
    private String empName;

    /**
     * 职位描述
     */
    @Excel(name = "职位描述")
    private String empDes;

    /**
     * 需求人数
     */
    @Excel(name = "需求人数")
    private Long empQuantity;

    /**
     * 薪资标准
     */
    @Excel(name = "薪资标准")
    private Long empPay;

    /**
     * 最高薪资
     */
    @Excel(name = "最高薪资")
    private Long empPayMax;

    /**
     * 福利待遇
     */
    @Excel(name = "福利待遇")
    private String empBonus;

    /**
     * 工作内容
     */
    @Excel(name = "工作内容")
    private String empConten;

    /**
     * 上岗需求
     */
    @Excel(name = "上岗需求")
    private String empReq;

    /**
     * 联系方式(fkey)
     */
    @Excel(name = "联系方式(fkey)")
    private Long cPhone;

    /**
     * 公司地址(fkey)
     */
    @Excel(name = "公司地址(fkey)")
    private String cAddress;

    /**
     * 就业信息详情
     */
    @Excel(name = "就业信息详情")
    private String empInfo;

    /**
     * 发布状态
     */
    @Excel(name = "发布状态")
    private String empStatus;

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpDes() {
        return empDes;
    }

    public void setEmpDes(String empDes) {
        this.empDes = empDes;
    }

    public Long getEmpQuantity() {
        return empQuantity;
    }

    public void setEmpQuantity(Long empQuantity) {
        this.empQuantity = empQuantity;
    }

    public Long getEmpPay() {
        return empPay;
    }

    public void setEmpPay(Long empPay) {
        this.empPay = empPay;
    }

    public Long getEmpPayMax() {
        return empPayMax;
    }

    public void setEmpPayMax(Long empPayMax) {
        this.empPayMax = empPayMax;
    }

    public String getEmpBonus() {
        return empBonus;
    }

    public void setEmpBonus(String empBonus) {
        this.empBonus = empBonus;
    }

    public String getEmpConten() {
        return empConten;
    }

    public void setEmpConten(String empConten) {
        this.empConten = empConten;
    }

    public String getEmpReq() {
        return empReq;
    }

    public void setEmpReq(String empReq) {
        this.empReq = empReq;
    }

    public Long getcPhone() {
        return cPhone;
    }

    public void setcPhone(Long cPhone) {
        this.cPhone = cPhone;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(String empInfo) {
        this.empInfo = empInfo;
    }

    public String getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(String empStatus) {
        this.empStatus = empStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("empId", getEmpId())
                .append("cName", getcName())
                .append("empName", getEmpName())
                .append("empDes", getEmpDes())
                .append("empQuantity", getEmpQuantity())
                .append("empPay", getEmpPay())
                .append("empBonus", getEmpBonus())
                .append("empConten", getEmpConten())
                .append("empReq", getEmpReq())
                .append("cPhone", getcPhone())
                .append("cAddress", getcAddress())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("empInfo", getEmpInfo())
                .append("empStatus", getEmpStatus())
                .toString();
    }
}
