package org.graduate.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.*;
import org.graduate.system.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.service.IBStudentService;

/**
 * 学生管理Service业务层处理
 * 
 * @author chuan
 * @date 2023-11-24
 */
@Service
public class BStudentServiceImpl implements IBStudentService 
{
    @Autowired
    private BStudentMapper bStudentMapper;
    @Autowired
    private BCompanyMapper bCompanyMapper;
    @Autowired
    private BTeacherMapper bTeacherMapper;
    @Autowired
    private BClassMapper b_classMapper;
    @Autowired
    private BSchoolMapper bSchoolMapper;

    /**
     * 查询学生管理
     * 
     * @param sId 学生管理主键
     * @return 学生管理
     */
    @Override
    public BStudent selectBStudentBySId(Long sId)
    {
        return bStudentMapper.selectBStudentBySId(sId);
    }

    /**
     * 查询学生管理列表
     * 
     * @param b_Student 学生管理
     * @return 学生管理
     */
    @Override
    public List<BStudent> selectBStudentList(BStudent b_Student)
    {
        List<BStudent> bStudent = bStudentMapper.selectBStudentList(b_Student);
        List<Long> lists = new ArrayList<>();
        /**
         * 公司姓名
         **/
        for(BStudent bc:bStudent) {
            lists.add(bc.getCompenyId());
        }
        //获取到公司列表
        List<BCompany> CompanyList= bCompanyMapper.selectBClassListCIds(lists);
        //将列表转换成集合
        Map<Long,BCompany> bCompanyMap = new HashMap<>();
        for(BCompany b:CompanyList) {
            bCompanyMap.put(b.getcId(),b);
        }

        /**
         * 教师姓名
         **/
        for(BStudent bc:bStudent) {
            lists.add(bc.getTeacherId());
        }
        //获取到教师列表
        List<BTeacher> teacherList= bTeacherMapper.selectBTeacherListById(lists);
        //将列表转换成集合
        Map<Long,BTeacher> teacherMap = new HashMap<>();
        for(BTeacher t:teacherList) {
            teacherMap.put(t.getTchrId(),t);
        }

        /**
         * 班级姓名
         **/
        for(BStudent bc:bStudent) {
            lists.add(bc.getClassId());
        }
        //获取到班级列表
        List<BClass> ClassList= b_classMapper.selectBClassListCIds(lists);
        //将列表转换成集合
        Map<Long, BClass> ClassMap = new HashMap<>();
        for(BClass c:ClassList) {
            ClassMap.put(c.getcId(),c);
        }
        /**
         * 学校姓名
         **/
        for(BStudent bc:bStudent) {
            lists.add(bc.getSchoolId());
        }
        //获取学校到列表
        List<BSchool> SchoolList= bSchoolMapper.selectBSchoolListSIds(lists);
        //将列表转换成集合
        Map<Long,BSchool> SchoolMap = new HashMap<>();
        for(BSchool b:SchoolList) {
            SchoolMap.put(b.getsId(),b);
        }

        //将map集合中 将名称取出 存入到list中
        for(BStudent bc:bStudent) {
            bc.setTeacherName(teacherMap.get(bc.getTeacherId()).getTchrName());
            bc.setCompenyName(bCompanyMap.get(bc.getCompenyId()).getcName());
            bc.setClassName(ClassMap.get(bc.getClassId()).getcName());
            bc.setSchoolName(SchoolMap.get(bc.getSchoolId()).getsName());
        }
        return bStudent;
    }

    /**
     * 新增学生管理
     * 
     * @param bStudent 学生管理
     * @return 结果
     */
    @Override
    public int insertBStudent(BStudent bStudent)
    {
        bStudent.setCreateTime(DateUtils.getNowDate());
        return bStudentMapper.insertBStudent(bStudent);
    }

    /**
     * 修改学生管理
     * 
     * @param bStudent 学生管理
     * @return 结果
     */
    @Override
    public int updateBStudent(BStudent bStudent)
    {
        bStudent.setUpdateTime(DateUtils.getNowDate());
        return bStudentMapper.updateBStudent(bStudent);
    }

    /**
     * 批量删除学生管理
     * 
     * @param sIds 需要删除的学生管理主键
     * @return 结果
     */
    @Override
    public int deleteBStudentBySIds(Long[] sIds)
    {
        return bStudentMapper.deleteBStudentBySIds(sIds);
    }

    /**
     * 删除学生管理信息
     * 
     * @param sId 学生管理主键
     * @return 结果
     */
    @Override
    public int deleteBStudentBySId(Long sId)
    {
        return bStudentMapper.deleteBStudentBySId(sId);
    }
}
