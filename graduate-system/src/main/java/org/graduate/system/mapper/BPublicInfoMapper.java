package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BPublicInfo;

/**
 * 公告管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public interface BPublicInfoMapper 
{
    /**
     * 查询公告管理
     * 
     * @param nId 公告管理主键
     * @return 公告管理
     */
    public BPublicInfo selectBPublicInfoByNId(Long nId);

    /**
     * 查询公告管理列表
     * 
     * @param bPublicInfo 公告管理
     * @return 公告管理集合
     */
    public List<BPublicInfo> selectBPublicInfoList(BPublicInfo bPublicInfo);

    /**
     * 新增公告管理
     * 
     * @param bPublicInfo 公告管理
     * @return 结果
     */
    public int insertBPublicInfo(BPublicInfo bPublicInfo);

    /**
     * 修改公告管理
     * 
     * @param bPublicInfo 公告管理
     * @return 结果
     */
    public int updateBPublicInfo(BPublicInfo bPublicInfo);

    /**
     * 删除公告管理
     * 
     * @param nId 公告管理主键
     * @return 结果
     */
    public int deleteBPublicInfoByNId(Long nId);

    /**
     * 批量删除公告管理
     * 
     * @param nIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBPublicInfoByNIds(Long[] nIds);
}
