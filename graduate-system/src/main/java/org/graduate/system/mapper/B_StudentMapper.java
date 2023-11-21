package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.B_Student;

/**
 * 学生管理Mapper接口
 * 
 * @author chuan
 * @date 2023-11-21
 */
public interface B_StudentMapper 
{
    /**
     * 查询学生管理
     * 
     * @param sId 学生管理主键
     * @return 学生管理
     */
    public B_Student selectB_StudentBySId(Long sId);

    /**
     * 查询学生管理列表
     * 
     * @param b_Student 学生管理
     * @return 学生管理集合
     */
    public List<B_Student> selectB_StudentList(B_Student b_Student);

    /**
     * 新增学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    public int insertB_Student(B_Student b_Student);

    /**
     * 修改学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    public int updateB_Student(B_Student b_Student);

    /**
     * 删除学生管理
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    public int deleteB_StudentBySId(Long sId);

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteB_StudentBySIds(Long[] sIds);
}
