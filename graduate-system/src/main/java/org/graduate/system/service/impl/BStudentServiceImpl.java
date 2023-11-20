package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BStudentMapper;
import org.graduate.system.domain.BStudent;
import org.graduate.system.service.IBStudentService;

/**
 * 学生Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@Service
public class BStudentServiceImpl implements IBStudentService 
{
    @Autowired
    private BStudentMapper bStudentMapper;

    /**
     * 查询学生
     * 
     * @param sId 学生主键
     * @return 学生
     */
    @Override
    public BStudent selectBStudentBySId(Long sId)
    {
        return bStudentMapper.selectBStudentBySId(sId);
    }

    /**
     * 查询学生列表
     * 
     * @param bStudent 学生
     * @return 学生
     */
    @Override
    public List<BStudent> selectBStudentList(BStudent bStudent)
    {
        return bStudentMapper.selectBStudentList(bStudent);
    }

    /**
     * 新增学生
     * 
     * @param bStudent 学生
     * @return 结果
     */
    @Override
    public int insertBStudent(BStudent bStudent)
    {
        bStudent.setCreateTime(DateUtils.getNowDate());
        return bStudentMapper.insertBStudent(bStudent);
    }

    /**
     * 修改学生
     * 
     * @param bStudent 学生
     * @return 结果
     */
    @Override
    public int updateBStudent(BStudent bStudent)
    {
        bStudent.setUpdateTime(DateUtils.getNowDate());
        return bStudentMapper.updateBStudent(bStudent);
    }

    /**
     * 批量删除学生
     * 
     * @param sIds 需要删除的学生主键
     * @return 结果
     */
    @Override
    public int deleteBStudentBySIds(Long[] sIds)
    {
        return bStudentMapper.deleteBStudentBySIds(sIds);
    }

    /**
     * 删除学生信息
     * 
     * @param sId 学生主键
     * @return 结果
     */
    @Override
    public int deleteBStudentBySId(Long sId)
    {
        return bStudentMapper.deleteBStudentBySId(sId);
    }
}
