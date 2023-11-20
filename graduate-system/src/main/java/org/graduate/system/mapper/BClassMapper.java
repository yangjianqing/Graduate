package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BClass;

/**
 * 班级Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public interface BClassMapper 
{
    /**
     * 查询班级
     * 
     * @param cId 班级主键
     * @return 班级
     */
    public BClass selectBClassByCId(Long cId);

    /**
     * 查询班级列表
     * 
     * @param bClass 班级
     * @return 班级集合
     */
    public List<BClass> selectBClassList(BClass bClass);

    /**
     * 新增班级
     * 
     * @param bClass 班级
     * @return 结果
     */
    public int insertBClass(BClass bClass);

    /**
     * 修改班级
     * 
     * @param bClass 班级
     * @return 结果
     */
    public int updateBClass(BClass bClass);

    /**
     * 删除班级
     * 
     * @param cId 班级主键
     * @return 结果
     */
    public int deleteBClassByCId(Long cId);

    /**
     * 批量删除班级
     * 
     * @param cIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBClassByCIds(Long[] cIds);
}
