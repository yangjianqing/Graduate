package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.BClass;
import org.graduate.system.mapper.BClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.service.IBClassService;

/**
 * 班级管理Service业务层处理
 * 
 * @author chuan
 * @date 2023-11-23
 */
@Service
public class BClassServiceImpl implements IBClassService 
{
    @Autowired
    private BClassMapper bClassMapper;

    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    @Override
    public BClass selectBClassByCId(Long cId)
    {
        return bClassMapper.selectBClassByCId(cId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param bClass 班级管理
     * @return 班级管理
     */
    @Override
    public List<BClass> selectBClassList(BClass bClass)
    {
        return bClassMapper.selectBClassList(bClass);
    }

    /**
     * 新增班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    @Override
    public int insertBClass(BClass bClass)
    {
        bClass.setCreateTime(DateUtils.getNowDate());
        return bClassMapper.insertBClass(bClass);
    }

    /**
     * 修改班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    @Override
    public int updateBClass(BClass bClass)
    {
        bClass.setUpdateTime(DateUtils.getNowDate());
        return bClassMapper.updateBClass(bClass);
    }

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCIds(Long[] cIds)
    {
        return bClassMapper.deleteBClassByCIds(cIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCId(Long cId)
    {
        return bClassMapper.deleteBClassByCId(cId);
    }
}
