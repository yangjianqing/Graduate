package org.graduate.system.service;

import java.util.List;
import org.graduate.system.domain.BStudent;

/**
 * 学生管理Service接口
 * 
 * @author chuan
 * @date 2023-11-21
 */
public interface IBStudentService
{
    /**
     * 查询学生管理
     * 
     * @param sId 学生管理主键
     * @return 学生管理
     */
    public BStudent selectB_StudentBySId(Long sId);

    /**
     * 查询学生管理列表
     * 
     * @param b_Student 学生管理
     * @return 学生管理集合
     */
    public List<BStudent> selectB_StudentList(BStudent b_Student);

    /**
     * 新增学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    public int insertB_Student(BStudent b_Student);

    /**
     * 修改学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    public int updateB_Student(BStudent b_Student);

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteB_StudentBySIds(Long[] sIds);

    /**
     * 删除学生管理信息
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    public int deleteB_StudentBySId(Long sId);
}
