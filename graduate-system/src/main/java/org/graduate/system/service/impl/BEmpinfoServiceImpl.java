package org.graduate.system.service.impl;

import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BEmpinfoMapper;
import org.graduate.system.domain.BEmpinfo;
import org.graduate.system.service.IBEmpinfoService;

/**
 * 就业信息发布Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@Service
public class BEmpinfoServiceImpl implements IBEmpinfoService
{
    @Autowired
    private BEmpinfoMapper bEmpinfoMapper;

    /**
     * 查询就业信息发布
     *
     * @param empId 就业信息发布主键
     * @return 就业信息发布
     */
    @Override
    public BEmpinfo selectBEmpinfoByEmpId(Long empId)
    {
        return bEmpinfoMapper.selectBEmpinfoByEmpId(empId);
    }

    /**
     * 查询就业信息发布列表
     *
     * @param bEmpinfo 就业信息发布
     * @return 就业信息发布
     */
    @Override
    public List<BEmpinfo> selectBEmpinfoList(BEmpinfo bEmpinfo)
    {
        return bEmpinfoMapper.selectBEmpinfoList(bEmpinfo);
    }

    /**
     * 新增就业信息发布
     *
     * @param bEmpinfo 就业信息发布
     * @return 结果
     */
    @Override
    public int insertBEmpinfo(BEmpinfo bEmpinfo)
    {
        bEmpinfo.setCreateTime(DateUtils.getNowDate());
        return bEmpinfoMapper.insertBEmpinfo(bEmpinfo);
    }

    /**
     * 修改就业信息发布
     *
     * @param bEmpinfo 就业信息发布
     * @return 结果
     */
    @Override
    public int updateBEmpinfo(BEmpinfo bEmpinfo)
    {
        bEmpinfo.setUpdateTime(DateUtils.getNowDate());
        return bEmpinfoMapper.updateBEmpinfo(bEmpinfo);
    }

    /**
     * 批量删除就业信息发布
     *
     * @param empIds 需要删除的就业信息发布主键
     * @return 结果
     */
    @Override
    public int deleteBEmpinfoByEmpIds(Long[] empIds)
    {
        return bEmpinfoMapper.deleteBEmpinfoByEmpIds(empIds);
    }

    /**
     * 删除就业信息发布信息
     *
     * @param empId 就业信息发布主键
     * @return 结果
     */
    @Override
    public int deleteBEmpinfoByEmpId(Long empId)
    {
        return bEmpinfoMapper.deleteBEmpinfoByEmpId(empId);
    }

    /**
     * 调整就业信息发布状态
     *
     * @param bEmpinfo 需要调整的主键
     * @return 结果
     */
    public int modifiedBEmpinfoByEmpId(BEmpinfo bEmpinfo) {
        return bEmpinfoMapper.modifiedBEmpinfoByEmpId(bEmpinfo);
    }


}
