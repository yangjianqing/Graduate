package org.graduate.system.service;

import java.util.List;
import org.graduate.system.domain.B_Class;

/**
 * 班级管理Service接口
 * 
 * @author chuan
 * @date 2023-11-21
 */
public interface IB_ClassService 
{
    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    public B_Class selectB_ClassByCId(Long cId);

    /**
     * 查询班级管理列表
     * 
     * @param b_Class 班级管理
     * @return 班级管理集合
     */
    public List<B_Class> selectB_ClassList(B_Class b_Class);

    /**
     * 新增班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    public int insertB_Class(B_Class b_Class);

    /**
     * 修改班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    public int updateB_Class(B_Class b_Class);

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键集合
     * @return 结果
     */
    public int deleteB_ClassByCIds(Long[] cIds);

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    public int deleteB_ClassByCId(Long cId);
}
