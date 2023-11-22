package org.graduate.system.mapper;

import java.util.List;
import org.graduate.system.domain.BSchool;

/**
 * 学校管理Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
public interface BSchoolMapper 
{
    /**
     * 查询学校管理
     * 
     * @param sId 学校管理主键
     * @return 学校管理
     */
    public BSchool selectBSchoolBySId(Long sId);

    /**
     * 查询学校管理列表
     * 
     * @param bSchool 学校管理
     * @return 学校管理集合
     */
    public List<BSchool> selectBSchoolList(BSchool bSchool);

    /**
     * 新增学校管理
     * 
     * @param bSchool 学校管理
     * @return 结果
     */
    public int insertBSchool(BSchool bSchool);

    /**
     * 修改学校管理
     * 
     * @param bSchool 学校管理
     * @return 结果
     */
    public int updateBSchool(BSchool bSchool);

    /**
     * 删除学校管理
     * 
     * @param sId 学校管理主键
     * @return 结果
     */
    public int deleteBSchoolBySId(Long sId);

    /**
     * 批量删除学校管理
     * 
     * @param sIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBSchoolBySIds(Long[] sIds);
    /**
     * 根据学校ids 获取信息
     * @param SchoolIds
     * @return
     */
    List<BSchool> selectBSchoolListSIds(List<Long> SchoolIds);
}
