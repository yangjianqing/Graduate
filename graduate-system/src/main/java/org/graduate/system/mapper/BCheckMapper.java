package org.graduate.system.mapper;

import org.graduate.system.domain.BCheck;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 签到Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
public interface BCheckMapper 
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
     * 删除签到
     * 
     * @param ckId 签到主键
     * @return 结果
     */
    public int deleteBCheckByCkId(Long ckId);

    /**
     * 批量删除签到
     * 
     * @param ckIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBCheckByCkIds(Long[] ckIds);

    /**
     * 统计类型数
     *
     * @param
     * @return 结果
     */
     Map<Integer,Integer> CountCkTpye();


}
