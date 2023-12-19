package org.graduate.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.BCompany;
import org.graduate.system.domain.BStudent;
import org.graduate.system.mapper.BCompanyMapper;
import org.graduate.system.mapper.BStudentMapper;
import org.graduate.system.service.IBSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BSchoolMapper;
import org.graduate.system.domain.BSchool;

/**
 * 学校管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-11-20
 */
@Service
public class BSchoolServiceImpl implements IBSchoolService {
    @Autowired
    private BSchoolMapper bSchoolMapper;
    @Autowired
    private BStudentMapper bStudentMapper;

    @Autowired
    private BCompanyMapper bCompanyMapper;

    /**
     * 查询学校管理
     *
     * @param sId 学校管理主键
     * @return 学校管理
     */
    @Override
    public BSchool selectBSchoolBySId(Long sId) {
        return bSchoolMapper.selectBSchoolBySId(sId);
    }

    /**
     * 查询学校管理列表
     *
     * @param bSchool 学校管理
     * @return 学校管理
     */
    @Override
    public List<BSchool> selectBSchoolList(BSchool bSchool) {
        return bSchoolMapper.selectBSchoolList(bSchool);
    }

    /**
     * 新增学校管理
     *
     * @param bSchool 学校管理
     * @return 结果
     */
    @Override
    public int insertBSchool(BSchool bSchool) {
        bSchool.setCreateTime(DateUtils.getNowDate());
        return bSchoolMapper.insertBSchool(bSchool);
    }

    /**
     * 修改学校管理
     *
     * @param bSchool 学校管理
     * @return 结果
     */
    @Override
    public int updateBSchool(BSchool bSchool) {
        bSchool.setUpdateTime(DateUtils.getNowDate());
        return bSchoolMapper.updateBSchool(bSchool);
    }

    /**
     * 批量删除学校管理
     *
     * @param sIds 需要删除的学校管理主键
     * @return 结果
     */
    @Override
    public int deleteBSchoolBySIds(Long[] sIds) {
        return bSchoolMapper.deleteBSchoolBySIds(sIds);
    }

    /**
     * 删除学校管理信息
     *
     * @param sId 学校管理主键
     * @return 结果
     */
    @Override
    public int deleteBSchoolBySId(Long sId) {
        return bSchoolMapper.deleteBSchoolBySId(sId);
    }

    @Override
    public List<BSchool> selectBSchoolAll() {
        return bSchoolMapper.selectBSchoolAll();
    }

    @Override
    public String selectSchoolName(String sId) {
        return bSchoolMapper.selectSchoolName(sId);
    }

    @Override
    public Map<String, Object> selectOtherInfo(BSchool bSchool) {
        Map<String, Object> map = new HashMap<>();
        //1、查询出符合条件的所有学生信息
        BStudent student = new BStudent();
        student.setSchoolId(bSchool.getsId());
        List<BStudent> bStudents = bStudentMapper.selectBStudentList(student);
        map.put("sTotal", bStudents.size());//总人数
        student.setcStatus(0L);
        bStudents = bStudentMapper.selectBStudentList(student);
        map.put("gTotal", bStudents.size());//就业人数
        //2、查询毕业生公司信息
        List<BCompany> companyList = bCompanyMapper.selectCompanyBySchoolId(bSchool.getsId());
        //公司去重
        List<BCompany> distinctCompanyList = companyList.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(BCompany::getcId))),
                        ArrayList::new
                ));
        map.put("company", distinctCompanyList);
        return map;
    }
}
