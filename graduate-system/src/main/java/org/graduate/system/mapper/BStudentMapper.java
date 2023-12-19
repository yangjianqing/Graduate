package org.graduate.system.mapper;

import java.util.List;
import java.util.Map;

import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.graduate.system.domain.BStudent;

/**
 * 学生管理Mapper接口
 * 
 * @author chuan
 * @date 2023-11-24
 */
public interface BStudentMapper 
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
     * 删除学生管理
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    public int deleteBStudentBySId(Long sId);

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBStudentBySIds(Long[] sIds);

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
    @MapKey("school_id")
    public List<Map<String,String>> selectBStudentCountMap();

    /**
     * 根据create_time(数据类型为datetime)查询出，每个月有多少个就业生,根据c_status=0
     *
     * @return 结果
     */
    @MapKey("month")
    public List<Map<String,String>> selectBStudentTimeMap();

    /**
     * 根据create_time(数据类型为datetime)查询出，每个月有多少个就业生,根据c_status=1
     *
     * @return 结果
     */
    @MapKey("month")
    public List<Map<String,String>> selectBStudentTimesMap();
}