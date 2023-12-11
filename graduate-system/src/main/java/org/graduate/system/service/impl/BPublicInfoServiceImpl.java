package org.graduate.system.service.impl;

import java.util.List;
import org.graduate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BPublicInfoMapper;
import org.graduate.system.domain.BPublicInfo;
import org.graduate.system.service.IBPublicInfoService;

/**
 * 公告管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@Service
public class BPublicInfoServiceImpl implements IBPublicInfoService 
{
    @Autowired
    private BPublicInfoMapper bPublicInfoMapper;

    /**
     * 查询公告管理
     * 
     * @param nId 公告管理主键
     * @return 公告管理
     */
    @Override
    public BPublicInfo selectBPublicInfoByNId(Long nId)
    {
        return bPublicInfoMapper.selectBPublicInfoByNId(nId);
    }

    /**
     * 查询公告管理列表
     * 
     * @param bPublicInfo 公告管理
     * @return 公告管理
     */
    @Override
    public List<BPublicInfo> selectBPublicInfoList(BPublicInfo bPublicInfo)
    {
        return bPublicInfoMapper.selectBPublicInfoList(bPublicInfo);
    }

    /**
     * 查询公告管理列表
     *
     * @param bPublicInfo 公告管理
     * @return 公告管理
     */
    @Override
    public List<BPublicInfo> queryBPublicInfoList(BPublicInfo bPublicInfo)
    {
        return bPublicInfoMapper.queryBPublicInfoList(bPublicInfo);
    }

    /**
     * 新增公告管理
     * 
     * @param bPublicInfo 公告管理
     * @return 结果
     */
    @Override
    public int insertBPublicInfo(BPublicInfo bPublicInfo)
    {
        bPublicInfo.setCreateTime(DateUtils.getNowDate());
        return bPublicInfoMapper.insertBPublicInfo(bPublicInfo);
    }

    /**
     * 修改公告管理
     * 
     * @param bPublicInfo 公告管理
     * @return 结果
     */
    @Override
    public int updateBPublicInfo(BPublicInfo bPublicInfo)
    {
        bPublicInfo.setUpdateTime(DateUtils.getNowDate());
        return bPublicInfoMapper.updateBPublicInfo(bPublicInfo);
    }

    /**
     * 批量删除公告管理
     * 
     * @param nIds 需要删除的公告管理主键
     * @return 结果
     */
    @Override
    public int deleteBPublicInfoByNIds(Long[] nIds)
    {
        return bPublicInfoMapper.deleteBPublicInfoByNIds(nIds);
    }

    /**
     * 删除公告管理信息
     * 
     * @param nId 公告管理主键
     * @return 结果
     */
    @Override
    public int deleteBPublicInfoByNId(Long nId)
    {
        return bPublicInfoMapper.deleteBPublicInfoByNId(nId);
    }
}
