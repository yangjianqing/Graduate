package org.graduate.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.graduate.system.mapper.BCheckMapper;
import org.graduate.system.domain.BCheck;
import org.graduate.system.service.IBCheckService;

/**
 * 签到Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@Service
public class BCheckServiceImpl implements IBCheckService 
{
    @Autowired
    private BCheckMapper bCheckMapper;
//    @Autowired
//    private BClassMapper bClassMapper;

    /**
     * 查询签到
     * 
     * @param ckId 签到主键
     * @return 签到
     */
    @Override
    public BCheck selectBCheckByCkId(Long ckId)
    {
        return bCheckMapper.selectBCheckByCkId(ckId);
    }

    /**
     * 查询签到列表
     * 
     * @param bCheck 签到
     * @return 签到
     */
    @Override
    public List<BCheck> selectBCheckList(BCheck bCheck)
    {
        /**
         * 班级姓名
         **/
        List<BCheck> bStudent = bCheckMapper.selectBCheckList(bCheck);
//        List<Long> lists = new ArrayList<>();
//        for(BCheck bc:bStudent) {
//            lists.add(bc.getbClass());
//        }
//        //获取到班级列表
//        List<BClass> ClassList= bClassMapper.selectBClassListCIds(lists);
//        //将列表转换成集合
//        Map<Long, BClass> ClassMap = new HashMap<>();
//        for(BClass c:ClassList) {
//            ClassMap.put(c.getcId(),c);
//        }
//
//        //将map集合中 教师名称取出 存入到list中
//        for(BCheck bc:bStudent) {bc.setbClassName(ClassMap.get(bc.getbClass()).getcName());}
        return bStudent;
//        return bCheckMapper.selectBCheckList(bCheck);
    }

    /**
     * 新增签到
     * 
     * @param bCheck 签到
     * @return 结果
     */
    @Override
    public int insertBCheck(BCheck bCheck)
    {
        return bCheckMapper.insertBCheck(bCheck);
    }

    /**
     * 修改签到
     * 
     * @param bCheck 签到
     * @return 结果
     */
    @Override
    public int updateBCheck(BCheck bCheck)
    {
        return bCheckMapper.updateBCheck(bCheck);
    }

    /**
     * 批量删除签到
     * 
     * @param ckIds 需要删除的签到主键
     * @return 结果
     */
    @Override
    public int deleteBCheckByCkIds(Long[] ckIds)
    {
        return bCheckMapper.deleteBCheckByCkIds(ckIds);
    }

    /**
     * 删除签到信息
     * 
     * @param ckId 签到主键
     * @return 结果
     */
    @Override
    public int deleteBCheckByCkId(Long ckId)
    {
        return bCheckMapper.deleteBCheckByCkId(ckId);
    }
}
