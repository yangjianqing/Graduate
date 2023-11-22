package org.graduate.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.BTeacher;
import org.graduate.system.mapper.BTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BClassMapper;
import org.graduate.system.domain.BClass;
import org.graduate.system.service.IBClassService;

/**
 * 班级管理Service业务层处理
 * 
 * @author chuan
 * @date 2023-11-21
 */
@Service
public class BClassServiceImpl implements IBClassService
{
    @Autowired
    private BClassMapper b_ClassMapper;
    @Autowired
    private BTeacherMapper bTeacherMapper;


    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    @Override
    public BClass selectB_ClassByCId(Long cId)
    {
        return b_ClassMapper.selectB_ClassByCId(cId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param b_Class 班级管理
     * @return 班级管理
     */
    @Override
    public List<BClass> selectB_ClassList(BClass b_Class)
    {
        List<BClass> bClasses = b_ClassMapper.selectB_ClassList(b_Class);
        List<Long> lists = new ArrayList<>();
        for(BClass bc:bClasses) {
            lists.add(bc.getTeacherId());
        }
        //获取到教师列表
       List<BTeacher> teacherList= bTeacherMapper.selectBTeacherListById(lists);
        //将列表转换成集合
        Map<Long,BTeacher> teacherMap = new HashMap<>();
        for(BTeacher t:teacherList) {
            teacherMap.put(t.getTchrId(),t);
        }
        //将map集合中 教师名称取出 存入到list中
        for(BClass bc:bClasses) {
            bc.setTeacherName(teacherMap.get(bc.getTeacherId()).getTchrName());
        }
        return bClasses;
    }

    /**
     * 新增班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    @Override
    public int insertB_Class(BClass b_Class)
    {
        b_Class.setCreateTime(DateUtils.getNowDate());
        return b_ClassMapper.insertB_Class(b_Class);
    }

    /**
     * 修改班级管理
     * 
     * @param b_Class 班级管理
     * @return 结果
     */
    @Override
    public int updateB_Class(BClass b_Class)
    {
        b_Class.setUpdateTime(DateUtils.getNowDate());
        return b_ClassMapper.updateB_Class(b_Class);
    }

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteB_ClassByCIds(Long[] cIds)
    {
        return b_ClassMapper.deleteB_ClassByCIds(cIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteB_ClassByCId(Long cId)
    {
        return b_ClassMapper.deleteB_ClassByCId(cId);
    }
}
