package org.graduate.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BCompanyMapper;
import org.graduate.system.domain.BCompany;
import org.graduate.system.service.IBCompanyService;

/**
 * 公司管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@Service
public class BCompanyServiceImpl implements IBCompanyService 
{
    @Autowired
    private BCompanyMapper bCompanyMapper;


    /**
     * 查询公司管理
     * 
     * @param cId 公司管理主键
     * @return 公司管理
     */
    @Override
    public BCompany selectBCompanyByCId(Long cId)
    {
        return bCompanyMapper.selectBCompanyByCId(cId);
    }

    /**
     * 查询公司管理列表
     * 
     * @param bCompany 公司管理
     * @return 公司管理
     */
    @Override
    public List<BCompany> selectBCompanyList(BCompany bCompany)
    {
        return bCompanyMapper.selectBCompanyList(bCompany);
    }

    /**
     * 新增公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    @Override
    public int insertBCompany(BCompany bCompany)
    {
        bCompany.setCreateTime(DateUtils.getNowDate());
        return bCompanyMapper.insertBCompany(bCompany);
    }

    /**
     * 修改公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    @Override
    public int updateBCompany(BCompany bCompany)
    {
        bCompany.setUpdateTime(DateUtils.getNowDate());
        return bCompanyMapper.updateBCompany(bCompany);
    }

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBCompanyByCIds(Long[] cIds)
    {
        return bCompanyMapper.deleteBCompanyByCIds(cIds);
    }

    /**
     * 删除公司管理信息
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBCompanyByCId(Long cId)
    {
        return bCompanyMapper.deleteBCompanyByCId(cId);
    }

    @Override
    public List<BCompany> selectBCompanyAll() {
        return bCompanyMapper.selectBCompanyAll();
    }
    /**
     * 查询公司经纬度
     *
     * @return 结果
     */
    @Override
    public List<Location> selectBCompanyByPoint() {
        return bCompanyMapper.selectBCompanyByPoint();
    }

    /**
     * 查询公司地址
     * @return
     */
    @Override
    public List<Map<String, Object>> getAllCompanyAddresses() {
        return bCompanyMapper.getAllCompanyAddresses();
    }
}


