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
     * 统计各个类型的数量
     *
     * @param
     * @return 结果
     */
    public List<Map<String,Object>> CountCkTpye();   /**
     * 统计教师下毕业人数
     *
     * @param
     * @return 结果
     */
    public List<Map<String,Object>> countStu();



    /**
     * 查询每天签到数量
     *
     * @return
     */
    public List<Map<String, Object>> selectCheckInCounts();
}
