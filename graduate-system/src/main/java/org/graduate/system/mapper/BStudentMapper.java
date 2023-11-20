package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BStudent;

/**
 * 学生Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public interface BStudentMapper 
{
    /**
     * 查询学生
     * 
     * @param sId 学生主键
     * @return 学生
     */
    public BStudent selectBStudentBySId(Long sId);

    /**
     * 查询学生列表
     * 
     * @param bStudent 学生
     * @return 学生集合
     */
    public List<BStudent> selectBStudentList(BStudent bStudent);

    /**
     * 新增学生
     * 
     * @param bStudent 学生
     * @return 结果
     */
    public int insertBStudent(BStudent bStudent);

    /**
     * 修改学生
     * 
     * @param bStudent 学生
     * @return 结果
     */
    public int updateBStudent(BStudent bStudent);

    /**
     * 删除学生
     * 
     * @param sId 学生主键
     * @return 结果
     */
    public int deleteBStudentBySId(Long sId);

    /**
     * 批量删除学生
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBStudentBySIds(Long[] sIds);
}
