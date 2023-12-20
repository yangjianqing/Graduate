package org.graduate.system.service.impl;

import java.util.*;

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
 * @date 2023-11-24
 */
@Service
public class BClassServiceImpl implements IBClassService 
{
    @Autowired
    private BClassMapper bClassMapper;
    @Autowired
    private BTeacherMapper bTeacherMapper;

    /**
     * 查询班级管理
     * 
     * @param cId 班级管理主键
     * @return 班级管理
     */
    @Override
    public BClass selectBClassByCId(Long cId)
    {
        return bClassMapper.selectBClassByCId(cId);
    }

    /**
     * 查询班级管理列表
     * 
     * @param bClass 班级管理
     * @return 班级管理
     */
    @Override
    public List<BClass> selectBClassList(BClass bClass)
    {
        List<BClass> bClasses = bClassMapper.selectBClassList(bClass);
        Set<Long> teacherIds = new HashSet<>();
        for(BClass bc:bClasses) {
            if(bc.getTeacherId() != null) {
                teacherIds.add(bc.getTeacherId());
            }
        }
        //获取到教师列表
        List<BTeacher> teacherList = new ArrayList<>();
        if(!teacherIds.isEmpty()) {
            teacherList = bTeacherMapper.selectBTeacherListById(new ArrayList<>(teacherIds));
        }
        //将列表转换成集合
        Map<Long,BTeacher> teacherMap = new HashMap<>();
        for(BTeacher t:teacherList) {
            teacherMap.put(t.getTchrId(),t);
        }
        //将map集合中 教师名称取出 存入到list中
        for(BClass bc:bClasses) {
            if(bc.getTeacherId() != null) {
                BTeacher teacher = teacherMap.get(bc.getTeacherId());
                if(teacher != null) {
                    bc.setTeacherName(teacher.getTchrName());
                }
            }
        }
        return bClasses;
    }

    /**
     * 新增班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    @Override
    public int insertBClass(BClass bClass)
    {
        bClass.setCreateTime(DateUtils.getNowDate());
        return bClassMapper.insertBClass(bClass);
    }

    /**
     * 修改班级管理
     * 
     * @param bClass 班级管理
     * @return 结果
     */
    @Override
    public int updateBClass(BClass bClass)
    {
        bClass.setUpdateTime(DateUtils.getNowDate());
        return bClassMapper.updateBClass(bClass);
    }

    /**
     * 批量删除班级管理
     * 
     * @param cIds 需要删除的班级管理主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCIds(Long[] cIds)
    {
        return bClassMapper.deleteBClassByCIds(cIds);
    }

    /**
     * 删除班级管理信息
     * 
     * @param cId 班级管理主键
     * @return 结果
     */
    @Override
    public int deleteBClassByCId(Long cId)
    {
        return bClassMapper.deleteBClassByCId(cId);
    }

    @Override
    public List<BClass> selectBClassAll() {
        return bClassMapper.selectBClassAll();
    }

    @Override
    public String selectClassName(String cId)
    {
        return bClassMapper.selectClassName(cId);
    }
}
