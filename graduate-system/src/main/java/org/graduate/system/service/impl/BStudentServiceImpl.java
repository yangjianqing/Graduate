package org.graduate.system.service.impl;

import java.util.*;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.*;
import org.graduate.system.mapper.*;
import org.graduate.system.service.IBCompanyService;
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
        BStudent student =  bStudentMapper.selectBStudentBySId(sId);
        try {
            List<Map<String,String>> list = bStudentMapper.findStudentInfo(sId);
            Map<String,String> map = list.get(0);
            student.setCompenyName(map.get("cName"));
            student.setClassName(map.get("sName"));
            student.setSchoolName(map.get("clName"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }

    /**
     * 查询学生管理列表
     *
     * @param b_Student 学生管理
     * @return 学生管理
     */
    @Override
    public List<BStudent> selectBStudentList(BStudent b_Student) {
        List<BStudent> bStudent = bStudentMapper.selectBStudentList(b_Student);
        Set<Long> companyIds = new HashSet<>();
        Set<Long> teacherIds = new HashSet<>();
        Set<Long> classIds = new HashSet<>();
        Set<Long> schoolIds = new HashSet();

        for (BStudent bc : bStudent) {
            if(bc.getCompenyId() != null) {
                companyIds.add(bc.getCompenyId());
            }
            if(bc.getTeacherId() != null) {
                teacherIds.add(bc.getTeacherId());
            }
            if(bc.getClassId() != null) {
                classIds.add(bc.getClassId());
            }
            if(bc.getSchoolId() != null) {
                schoolIds.add(bc.getSchoolId());
            }
        }

        // 获取公司、教师、班级、学校列表
        List<BCompany> CompanyList = new ArrayList<>();
        if(!companyIds.isEmpty()) {
            CompanyList = bCompanyMapper.selectBClassListCIds(new ArrayList<>(companyIds));
        }
        List<BTeacher> teacherList = new ArrayList<>();
        if(!teacherIds.isEmpty()) {
            teacherList = bTeacherMapper.selectBTeacherListById(new ArrayList<>(teacherIds));
        }
        List<BClass> ClassList = new ArrayList<>();
        if(!classIds.isEmpty()) {
            ClassList = b_classMapper.selectBClassListCIds(new ArrayList<>(classIds));
        }
        List<BSchool> SchoolList = new ArrayList<>();
        if(!schoolIds.isEmpty()) {
            SchoolList = bSchoolMapper.selectBSchoolListSIds(new ArrayList<>(schoolIds));
        }

        Map<Long, BCompany> bCompanyMap = new HashMap<>();
        for (BCompany b : CompanyList) {
            bCompanyMap.put(b.getcId(), b);
        }

        Map<Long, BTeacher> teacherMap = new HashMap<>();
        for (BTeacher t : teacherList) {
            teacherMap.put(t.getTchrId(), t);
        }

        Map<Long, BClass> ClassMap = new HashMap<>();
        for (BClass c : ClassList) {
            ClassMap.put(c.getcId(), c);
        }

        Map<Long, BSchool> SchoolMap = new HashMap<>();
        for (BSchool b : SchoolList) {
            SchoolMap.put(b.getsId(), b);
        }

        // 设置学生的名称信息
        for (BStudent bc : bStudent) {
            if(bc.getTeacherId() != null) {
                BTeacher teacher = teacherMap.get(bc.getTeacherId());
                if(teacher != null) {
                    bc.setTeacherName(teacher.getTchrName());
                }
            }
            if(bc.getCompenyId() != null) {
                BCompany company = bCompanyMap.get(bc.getCompenyId());
                if(company != null) {
                    bc.setCompenyName(company.getcName());
                }
            }
            if(bc.getClassId() != null) {
                BClass bClass = ClassMap.get(bc.getClassId());
                if(bClass != null) {
                    bc.setClassName(bClass.getcName());
                }
            }
            if(bc.getSchoolId() != null) {
                BSchool school = SchoolMap.get(bc.getSchoolId());
                if(school != null) {
                    bc.setSchoolName(school.getsName());
                }
            }
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
    public int insertBStudent(BStudent bStudent) {
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
    public int updateBStudent(BStudent bStudent) {
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

    /**
     * 根据手机号查询学生对象 顺便查询出学生对应的公司信息
     * @param sNumbers 电话
     * @return
     */
    @Override
    public BStudent selectBStudentPhone(String sNumbers) {
        BCompany company = new BCompany();
        company.setcPhone(sNumbers);
        List<BCompany> bCompanies = bCompanyMapper.selectBCompanyList(company); //学生对应的公司
        BStudent bStudent = bStudentMapper.selectBStudentPhone(sNumbers);
        bStudent.setCompanyList(bCompanies);
        return bStudent;
    }

    @Override
    public List<Map<String,String>> selectBStudentCountMap()
    {
        return bStudentMapper.selectBStudentCountMap();
    }

    @Override
    public List<Map<String, String>> selectBStudentTimeMap()
    {
        return bStudentMapper.selectBStudentTimeMap();
    }

    @Override
    public List<Map<String, String>> selectBStudentTimesMap()
    {
        return bStudentMapper.selectBStudentTimesMap();
    }

    @Override
    public List<String> AllMailbox()
    {
        return bStudentMapper.AllMailbox();
    }
}
