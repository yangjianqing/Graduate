package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.B_ClassMapper;
import org.graduate.system.domain.B_Class;
import org.graduate.system.service.IB_ClassService;

/**
 * 班级管理Service业务层处理
 * 
 * @author chuan
 * @date 2023-11-21
 */
@Service
public class B_ClassServiceImpl implements IB_ClassService 
{
    @Autowired
    private B_ClassMapper b_ClassMapper;

    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    @Override
    public B_Class selectB_ClassByCId(Long cId)
    {
        return b_ClassMapper.selectB_ClassByCId(cId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param b_Class 班级管理
     * @return 班级管理
     */
    @Override
    public List<B_Class> selectB_ClassList(B_Class b_Class)
    {
        return b_ClassMapper.selectB_ClassList(b_Class);
    }

    /**
     * 新增班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    @Override
    public int insertB_Class(B_Class b_Class)
    {
        b_Class.setCreateTime(DateUtils.getNowDate());
        return b_ClassMapper.insertB_Class(b_Class);
    }

    /**
     * 修改班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    @Override
    public int updateB_Class(B_Class b_Class)
    {
        b_Class.setUpdateTime(DateUtils.getNowDate());
        return b_ClassMapper.updateB_Class(b_Class);
    }

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteB_ClassByCIds(Long[] cIds)
    {
        return b_ClassMapper.deleteB_ClassByCIds(cIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteB_ClassByCId(Long cId)
    {
        return b_ClassMapper.deleteB_ClassByCId(cId);
    }
}
