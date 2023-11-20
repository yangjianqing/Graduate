package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BClassMapper;
import org.graduate.system.domain.BClass;
import org.graduate.system.service.IBClassService;

/**
 * 班级Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@Service
public class BClassServiceImpl implements IBClassService 
{
    @Autowired
    private BClassMapper bClassMapper;

    /**
     * 查询班级
     * 
     * @param cId 班级主键
     * @return 班级
     */
    @Override
    public BClass selectBClassByCId(Long cId)
    {
        return bClassMapper.selectBClassByCId(cId);
    }

    /**
     * 查询班级列表
     * 
     * @param bClass 班级
     * @return 班级
     */
    @Override
    public List<BClass> selectBClassList(BClass bClass)
    {
        return bClassMapper.selectBClassList(bClass);
    }

    /**
     * 新增班级
     * 
     * @param bClass 班级
     * @return 结果
     */
    @Override
    public int insertBClass(BClass bClass)
    {
        bClass.setCreateTime(DateUtils.getNowDate());
        return bClassMapper.insertBClass(bClass);
    }

    /**
     * 修改班级
     * 
     * @param bClass 班级
     * @return 结果
     */
    @Override
    public int updateBClass(BClass bClass)
    {
        bClass.setUpdateTime(DateUtils.getNowDate());
        return bClassMapper.updateBClass(bClass);
    }

    /**
     * 批量删除班级
     * 
     * @param cIds 需要删除的班级主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCIds(Long[] cIds)
    {
        return bClassMapper.deleteBClassByCIds(cIds);
    }

    /**
     * 删除班级信息
     * 
     * @param cId 班级主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCId(Long cId)
    {
        return bClassMapper.deleteBClassByCId(cId);
    }
}
