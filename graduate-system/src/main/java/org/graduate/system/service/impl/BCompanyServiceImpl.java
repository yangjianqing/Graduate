package org.graduate.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.graduate.common.utils.DateUtils;
import org.graduate.system.domain.BClass;
import org.graduate.system.mapper.BClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BCompanyMapper;
import org.graduate.system.domain.BCompany;
import org.graduate.system.service.IBCompanyService;

/**
 * 公司管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@Service
public class BCompanyServiceImpl implements IBCompanyService 
{
    @Autowired
    private BCompanyMapper bCompanyMapper;

    /**
     * 查询公司管理
     * 
     * @param cId 公司管理主键
     * @return 公司管理
     */
    @Override
    public BCompany selectBCompanyByCId(Long cId)
    {
        return bCompanyMapper.selectBCompanyByCId(cId);
    }

    /**
     * 查询公司管理列表
     * 
     * @param bCompany 公司管理
     * @return 公司管理
     */
    @Override
    public List<BCompany> selectBCompanyList(BCompany bCompany)
    {
//        /**
//         * 班级姓名
//         **/
//        List<BCompany> bStudent = bCompanyMapper.selectBCompanyList(bCompany);
//        List<Long> lists = new ArrayList<>();
//
//
//        for(BCompany bc:bStudent) {
//            lists.add(bc.getcId());
//        }
//        //获取到班级列表
//        List<BClass> ClassList= bClassMapper.selectBClassListCIds(lists);
//        //将列表转换成集合
//        Map<Long, BClass> ClassMap = new HashMap<>();
//        for(BClass c:ClassList) {
//            ClassMap.put(c.getcId(),c);
//        }
//
//        //将map集合中 教师名称取出 存入到list中
//        for(BCompany bc:bStudent) {bc.setClassName(ClassMap.get(bc.getClassId()).getcName());}
//
//        return bStudent;

        return bCompanyMapper.selectBCompanyList(bCompany);
    }

    /**
     * 新增公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    @Override
    public int insertBCompany(BCompany bCompany)
    {
        bCompany.setCreateTime(DateUtils.getNowDate());
        return bCompanyMapper.insertBCompany(bCompany);
    }

    /**
     * 修改公司管理
     * 
     * @param bCompany 公司管理
     * @return 结果
     */
    @Override
    public int updateBCompany(BCompany bCompany)
    {
        bCompany.setUpdateTime(DateUtils.getNowDate());
        return bCompanyMapper.updateBCompany(bCompany);
    }

    /**
     * 批量删除公司管理
     * 
     * @param cIds 需要删除的公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBCompanyByCIds(Long[] cIds)
    {
        return bCompanyMapper.deleteBCompanyByCIds(cIds);
    }

    /**
     * 删除公司管理信息
     * 
     * @param cId 公司管理主键
     * @return 结果
     */
    @Override
    public int deleteBCompanyByCId(Long cId)
    {
        return bCompanyMapper.deleteBCompanyByCId(cId);
    }
}
