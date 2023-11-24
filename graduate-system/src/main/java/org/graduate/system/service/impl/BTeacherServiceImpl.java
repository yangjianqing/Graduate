package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.graduate.system.service.IBTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BTeacherMapper;
import org.graduate.system.domain.BTeacher;

/**
 * 教师信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@Service
public class BTeacherServiceImpl implements IBTeacherService
{
    @Autowired
    private BTeacherMapper bTeacherMapper;

    /**
     * 查询教师信息
     * 
     * @param tchrId 教师信息主键
     * @return 教师信息
     */
    @Override
    public BTeacher selectBTeacherByTchrId(Long tchrId)
    {
        return bTeacherMapper.selectBTeacherByTchrId(tchrId);
    }

    /**
     * 查询教师信息列表
     * 
     * @param bTeacher 教师信息
     * @return 教师信息
     */
    @Override
    public List<BTeacher> selectBTeacherList(BTeacher bTeacher)
    {
        return bTeacherMapper.selectBTeacherList(bTeacher);
    }

    /**
     * 新增教师信息
     * 
     * @param bTeacher 教师信息
     * @return 结果
     */
    @Override
    public int insertBTeacher(BTeacher bTeacher)
    {
        bTeacher.setCreateTime(DateUtils.getNowDate());
        return bTeacherMapper.insertBTeacher(bTeacher);
    }

    /**
     * 修改教师信息
     * 
     * @param bTeacher 教师信息
     * @return 结果
     */
    @Override
    public int updateBTeacher(BTeacher bTeacher)
    {
        bTeacher.setUpdateTime(DateUtils.getNowDate());
        return bTeacherMapper.updateBTeacher(bTeacher);
    }

    /**
     * 批量删除教师信息
     * 
     * @param tchrIds 需要删除的教师信息主键
     * @return 结果
     */
    @Override
    public int deleteBTeacherByTchrIds(Long[] tchrIds)
    {
        return bTeacherMapper.deleteBTeacherByTchrIds(tchrIds);
    }

    /**
     * 删除教师信息信息
     * 
     * @param tchrId 教师信息主键
     * @return 结果
     */
    @Override
    public int deleteBTeacherByTchrId(Long tchrId)
    {
        return bTeacherMapper.deleteBTeacherByTchrId(tchrId);
    }

    @Override
    public List<BTeacher> selectBTeacherAll() {
        return bTeacherMapper.selectBTeacherAll();
    }
}
