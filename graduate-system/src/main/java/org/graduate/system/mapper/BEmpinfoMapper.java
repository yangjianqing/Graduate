package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BEmpinfo;

/**
 * 就业信息发布Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-21
 */
public interface BEmpinfoMapper 
{
    /**
     * 查询就业信息发布
     * 
     * @param empId 就业信息发布主键
     * @return 就业信息发布
     */
    public BEmpinfo selectBEmpinfoByEmpId(Long empId);

    /**
     * 查询就业信息发布列表
     * 
     * @param bEmpinfo 就业信息发布
     * @return 就业信息发布集合
     */
    public List<BEmpinfo> selectBEmpinfoList(BEmpinfo bEmpinfo);

    /**
     * 新增就业信息发布
     * 
     * @param bEmpinfo 就业信息发布
     * @return 结果
     */
    public int insertBEmpinfo(BEmpinfo bEmpinfo);

    /**
     * 修改就业信息发布
     * 
     * @param bEmpinfo 就业信息发布
     * @return 结果
     */
    public int updateBEmpinfo(BEmpinfo bEmpinfo);

    /**
     * 删除就业信息发布
     * 
     * @param empId 就业信息发布主键
     * @return 结果
     */
    public int deleteBEmpinfoByEmpId(Long empId);

    /**
     * 批量删除就业信息发布
     * 
     * @param empIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBEmpinfoByEmpIds(Long[] empIds);
}
