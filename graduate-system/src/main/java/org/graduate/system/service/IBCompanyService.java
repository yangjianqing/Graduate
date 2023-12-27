package org.graduate.system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.graduate.system.domain.BCompany;
import org.graduate.system.domain.Location;

/**
 * 公司管理Service接口
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
public interface IBCompanyService 
{
    /**
     * 查询公司管理
     * 
     * @param cId 公司管理主键
     * @return 公司管理
     */
    public BCompany selectBCompanyByCId(Long cId);

    /**
     * 查询公司管理列表
     * 
     * @param bCompany 公司管理
     * @return 公司管理集合
     */
    public List<BCompany> selectBCompanyList(BCompany bCompany);

    /**
     * 新增公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    public int insertBCompany(BCompany bCompany);

    /**
     * 修改公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    public int updateBCompany(BCompany bCompany);

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的公司管理主键集合
     * @return 结果
     */
    public int deleteBCompanyByCIds(Long[] cIds);

    /**
     * 删除公司管理信息
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    public int deleteBCompanyByCId(Long cId);

    /**
     * 查询所有公司
     * @return
     */
    public List<BCompany> selectBCompanyAll();
    /**
     * 查询公司经纬度
     * @return 公司坐标列表
     */
    List<Location> selectBCompanyByPoint();


    /**
     * 查询公司地址
     * @return
     */
    List<Map<String, Object>> getAllCompanyAddresses();
}
