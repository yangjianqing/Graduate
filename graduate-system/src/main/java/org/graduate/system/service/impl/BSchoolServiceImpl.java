package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
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
public class BSchoolServiceImpl implements IBSchoolService
{
    @Autowired
    private BSchoolMapper bSchoolMapper;

    /**
     * 查询学校管理
     * 
     * @param sId 学校管理主键
     * @return 学校管理
     */
    @Override
    public BSchool selectBSchoolBySId(Long sId)
    {
        return bSchoolMapper.selectBSchoolBySId(sId);
    }

    /**
     * 查询学校管理列表
     * 
     * @param bSchool 学校管理
     * @return 学校管理
     */
    @Override
    public List<BSchool> selectBSchoolList(BSchool bSchool)
    {
        return bSchoolMapper.selectBSchoolList(bSchool);
    }

    /**
     * 新增学校管理
     * 
     * @param bSchool 学校管理
     * @return 结果
     */
    @Override
    public int insertBSchool(BSchool bSchool)
    {
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
    public int updateBSchool(BSchool bSchool)
    {
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
    public int deleteBSchoolBySIds(Long[] sIds)
    {
        return bSchoolMapper.deleteBSchoolBySIds(sIds);
    }

    /**
     * 删除学校管理信息
     * 
     * @param sId 学校管理主键
     * @return 结果
     */
    @Override
    public int deleteBSchoolBySId(Long sId)
    {
        return bSchoolMapper.deleteBSchoolBySId(sId);
    }

    @Override
    public List<BSchool> selectBSchoolAll() {
        return bSchoolMapper.selectBSchoolAll();
    }

    @Override
    public String selectSchoolName(String sId)
    {
        return bSchoolMapper.selectSchoolName(sId);
    }
}
