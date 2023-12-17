package org.graduate.system.service;

import org.graduate.system.domain.BCheck;

import java.util.List;
import java.util.Map;

/**
 * 签到Service接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface IBCheckService 
{
    /**
     * 查询签到
     * 
     * @param ckId 签到主键
     * @return 签到
     */
    public BCheck selectBCheckByCkId(Long ckId);

    /**
     * 查询签到列表
     * 
     * @param bCheck 签到
     * @return 签到集合
     */
    public List<BCheck> selectBCheckList(BCheck bCheck);

    /**
     * 新增签到
     * 
     * @param bCheck 签到
     * @return 结果
     */
    public int insertBCheck(BCheck bCheck);

    /**
     * 修改签到
     * 
     * @param bCheck 签到
     * @return 结果
     */
    public int updateBCheck(BCheck bCheck);

    /**
     * 批量删除签到
     * 
     * @param ckIds 需要删除的签到主键集合
     * @return 结果
     */
    public int deleteBCheckByCkIds(Long[] ckIds);

    /**
     * 删除签到信息
     * 
     * @param ckId 签到主键
     * @return 结果
     */
    public int deleteBCheckByCkId(Long ckId);

    /**
     * 删除签到信息
     *
     * @param
     * @return 结果
     */
    public Map<Integer,Integer> CountCkTpye();

}
