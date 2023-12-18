package org.graduate.system.service;

import java.util.List;
import java.util.Map;

import org.graduate.system.domain.BStudent;

/**
 * 学生管理Service接口
 * 
 * @author chuan
 * @date 2023-11-24
 */
public interface IBStudentService 
{
    /**
     * 查询学生管理
     * 
     * @param sId 学生管理主键
     * @return 学生管理
     */
    public BStudent selectBStudentBySId(Long sId);

    /**
     * 查询学生管理列表
     * 
     * @param bStudent 学生管理
     * @return 学生管理集合
     */
    public List<BStudent> selectBStudentList(BStudent bStudent);

    /**
     * 新增学生管理
     * 
     * @param bStudent 学生管理
     * @return 结果
     */
    public int insertBStudent(BStudent bStudent);

    /**
     * 修改学生管理
     * 
     * @param bStudent 学生管理
     * @return 结果
     */
    public int updateBStudent(BStudent bStudent);

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的学生管理主键集合
     * @return 结果
     */
    public int deleteBStudentBySIds(Long[] sIds);

    /**
     * 删除学生管理信息
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    public int deleteBStudentBySId(Long sId);

    /**
     * 根据电话号码查询学生信息
     *
     * @param sNumbers 电话
     * @return 结果
     */
    public BStudent selectBStudentPhone(String sNumbers);

    /**
     * 根据每个school_id学习id，查询里面有多少个就业的学生(c_status=0)，利用cont计数
     *
     * @return 结果
     */
    public List<Map<String,String>> selectBStudentCountMap();
}
