package org.graduate.active.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.active.mapper.YActivityMapper;
import org.graduate.active.domain.YActivity;
import org.graduate.active.service.IYActivityService;

/**
 * 活动信息Service业务层处理
 * 
 * @author yang
 * @date 2023-04-11
 */
@Service
public class YActivityServiceImpl implements IYActivityService 
{
    @Autowired
    private YActivityMapper yActivityMapper;

    /**
     * 查询活动信息
     * 
     * @param id 活动信息主键
     * @return 活动信息
     */
    @Override
    public YActivity selectYActivityById(Long id)
    {
        return yActivityMapper.selectYActivityById(id);
    }

    /**
     * 查询活动信息列表
     * 
     * @param yActivity 活动信息
     * @return 活动信息
     */
    @Override
    public List<YActivity> selectYActivityList(YActivity yActivity)
    {
        return yActivityMapper.selectYActivityList(yActivity);
    }

    /**
     * 新增活动信息
     * 
     * @param yActivity 活动信息
     * @return 结果
     */
    @Override
    public int insertYActivity(YActivity yActivity)
    {
        return yActivityMapper.insertYActivity(yActivity);
    }

    /**
     * 修改活动信息
     * 
     * @param yActivity 活动信息
     * @return 结果
     */
    @Override
    public int updateYActivity(YActivity yActivity)
    {
        return yActivityMapper.updateYActivity(yActivity);
    }

    /**
     * 批量删除活动信息
     * 
     * @param ids 需要删除的活动信息主键
     * @return 结果
     */
    @Override
    public int deleteYActivityByIds(Long[] ids)
    {
        return yActivityMapper.deleteYActivityByIds(ids);
    }

    /**
     * 删除活动信息信息
     * 
     * @param id 活动信息主键
     * @return 结果
     */
    @Override
    public int deleteYActivityById(Long id)
    {
        return yActivityMapper.deleteYActivityById(id);
    }
}
