package org.graduate.system.service;

import org.graduate.system.domain.BClass;

import java.util.List;

/**
 * 班级管理Service接口
 * 
 * @author chuan
 * @date 2023-11-23
 */
public interface IBClassService 
{
    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    public BClass selectBClassByCId(Long cId);

    /**
     * 查询班级管理列表
     * 
     * @param bClass 班级管理
     * @return 班级管理集合
     */
    public List<BClass> selectBClassList(BClass bClass);

    /**
     * 新增班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    public int insertBClass(BClass bClass);

    /**
     * 修改班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    public int updateBClass(BClass bClass);

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键集合
     * @return 结果
     */
    public int deleteBClassByCIds(Long[] cIds);

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    public int deleteBClassByCId(Long cId);
}
