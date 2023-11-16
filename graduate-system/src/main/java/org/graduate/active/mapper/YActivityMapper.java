package org.graduate.active.mapper;

import java.util.List;
import org.graduate.active.domain.YActivity;

/**
 * 活动信息Mapper接口
 * 
 * @author yang
 * @date 2023-04-11
 */
public interface YActivityMapper 
{
    /**
     * 查询活动信息
     * 
     * @param id 活动信息主键
     * @return 活动信息
     */
    public YActivity selectYActivityById(Long id);

    /**
     * 查询活动信息列表
     * 
     * @param yActivity 活动信息
     * @return 活动信息集合
     */
    public List<YActivity> selectYActivityList(YActivity yActivity);

    /**
     * 新增活动信息
     * 
     * @param yActivity 活动信息
     * @return 结果
     */
    public int insertYActivity(YActivity yActivity);

    /**
     * 修改活动信息
     * 
     * @param yActivity 活动信息
     * @return 结果
     */
    public int updateYActivity(YActivity yActivity);

    /**
     * 删除活动信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    public int deleteYActivityById(Long id);

    /**
     * 批量删除活动信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYActivityByIds(Long[] ids);
}
