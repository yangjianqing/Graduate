package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.graduate.system.service.IB_StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.B_StudentMapper;
import org.graduate.system.domain.B_Student;

/**
 * 学生管理Service业务层处理
 * 
 * @author chuan
 * @date 2023-11-21
 */
@Service
public class B_StudentServiceImpl implements IB_StudentService
{
    @Autowired
    private B_StudentMapper b_StudentMapper;

    /**
     * 查询学生管理
     * 
     * @param sId 学生管理主键
     * @return 学生管理
     */
    @Override
    public B_Student selectB_StudentBySId(Long sId)
    {
        return b_StudentMapper.selectB_StudentBySId(sId);
    }

    /**
     * 查询学生管理列表
     * 
     * @param b_Student 学生管理
     * @return 学生管理
     */
    @Override
    public List<B_Student> selectB_StudentList(B_Student b_Student)
    {
        return b_StudentMapper.selectB_StudentList(b_Student);
    }

    /**
     * 新增学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    @Override
    public int insertB_Student(B_Student b_Student)
    {
        b_Student.setCreateTime(DateUtils.getNowDate());
        return b_StudentMapper.insertB_Student(b_Student);
    }

    /**
     * 修改学生管理
     * 
     * @param b_Student 学生管理
     * @return 结果
     */
    @Override
    public int updateB_Student(B_Student b_Student)
    {
        b_Student.setUpdateTime(DateUtils.getNowDate());
        return b_StudentMapper.updateB_Student(b_Student);
    }

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteB_StudentBySIds(Long[] sIds)
    {
        return b_StudentMapper.deleteB_StudentBySIds(sIds);
    }

    /**
     * 删除学生管理信息
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteB_StudentBySId(Long sId)
    {
        return b_StudentMapper.deleteB_StudentBySId(sId);
    }
}
