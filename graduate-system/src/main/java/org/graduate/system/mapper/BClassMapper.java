package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BClass;

/**
 * 班级管理Mapper接口
 * 
 * @author chuan
 * @date 2023-11-24
 */
public interface BClassMapper 
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
     * 删除班级管理
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    public int deleteBClassByCId(Long cId);

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBClassByCIds(Long[] cIds);

    /**
     * 根据班级ids 获取信息
     * @param ClassIds
     * @return
     */
    List<BClass> selectBClassListCIds(List<Long> ClassIds);

    /**
     * 查询所有班級
     * @return
     */
    public List<BClass> selectBClassAll();
}
