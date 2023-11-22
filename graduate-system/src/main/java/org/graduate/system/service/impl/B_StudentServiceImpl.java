package org.graduate.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.*;
import org.graduate.system.mapper.*;
import org.graduate.system.service.IB_StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Autowired
    private BCompanyMapper bCompanyMapper;
    @Autowired
    private BTeacherMapper bTeacherMapper;
    @Autowired
    private B_ClassMapper b_classMapper;
    @Autowired
    private BSchoolMapper bSchoolMapper;
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
        List<B_Student> bStudent = b_StudentMapper.selectB_StudentList(b_Student);
        List<Long> lists = new ArrayList<>();
        /**
         * 公司姓名
         **/
        for(B_Student bc:bStudent) {
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
        for(B_Student bc:bStudent) {
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
        for(B_Student bc:bStudent) {
            lists.add(bc.getClassId());
        }
        //获取到班级列表
        List<B_Class> ClassList= b_classMapper.selectBClassListCIds(lists);
        //将列表转换成集合
        Map<Long,B_Class> ClassMap = new HashMap<>();
        for(B_Class c:ClassList) {
            ClassMap.put(c.getcId(),c);
        }
        //将map集合中 班级名称取出 存入到list中
        for(B_Student bc:bStudent) {
            bc.setClassName(ClassMap.get(bc.getClassId()).getcName());
        }
        /**
         * 学校姓名
         **/
        for(B_Student bc:bStudent) {
            lists.add(bc.getSchoolId());
        }
        //获取学校到列表
        List<BSchool> SchoolList= bSchoolMapper.selectBSchoolListSIds(lists);
        //将列表转换成集合
        Map<Long,BSchool> SchoolMap = new HashMap<>();
        for(BSchool b:SchoolList) {
            SchoolMap.put(b.getsId(),b);
        }

        //将map集合中 教师名称取出 存入到list中
        for(B_Student bc:bStudent) {
            bc.setTeacherName(teacherMap.get(bc.getTeacherId()).getTchrName());
            bc.setCompenyName(bCompanyMap.get(bc.getCompenyId()).getcName());
            bc.setSchoolName(SchoolMap.get(bc.getSchoolId()).getsName());
        }
        return bStudent;
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
