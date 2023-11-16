package org.graduate.active.service;

import java.util.List;
import org.graduate.active.domain.YActivity;

/**
 * 活动信息Service接口
 * 
 * @author yang
 * @date 2023-04-11
 */
public interface IYActivityService 
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
     * 批量删除活动信息
     * 
     * @param ids 需要删除的活动信息主键集合
     * @return 结果
     */
    public int deleteYActivityByIds(Long[] ids);

    /**
     * 删除活动信息信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    public int deleteYActivityById(Long id);
}
