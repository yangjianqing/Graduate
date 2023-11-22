package org.graduate.system.service;

import java.util.List;

import org.graduate.system.domain.BCompany;
import org.graduate.system.domain.BTeacher;

/**
 * 教师信息Service接口
 * 
 * @author ruoyi
 * @date 2023-11-21
 */
public interface IBTeacherService 
{
    /**
     * 查询教师信息
     * 
     * @param tchrId 教师信息主键
     * @return 教师信息
     */
    public BTeacher selectBTeacherByTchrId(Long tchrId);

    /**
     * 查询教师信息列表
     * 
     * @param bTeacher 教师信息
     * @return 教师信息集合
     */
    public List<BTeacher> selectBTeacherList(BTeacher bTeacher);

    /**
     * 新增教师信息
     * 
     * @param bTeacher 教师信息
     * @return 结果
     */
    public int insertBTeacher(BTeacher bTeacher);

    /**
     * 修改教师信息
     * 
     * @param bTeacher 教师信息
     * @return 结果
     */
    public int updateBTeacher(BTeacher bTeacher);

    /**
     * 批量删除教师信息
     * 
     * @param tchrIds 需要删除的教师信息主键集合
     * @return 结果
     */
    public int deleteBTeacherByTchrIds(Long[] tchrIds);

    /**
     * 删除教师信息信息
     * 
     * @param tchrId 教师信息主键
     * @return 结果
     */
    public int deleteBTeacherByTchrId(Long tchrId);

    /**
     * 查询所有老师
     * @return
     */
    public List<BTeacher> selectBTeacherAll();
}
