package org.graduate.web.controller;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.graduate.common.annotation.Anonymous;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.system.domain.*;
import org.graduate.common.constant.CacheConstants;
import org.graduate.common.constant.Constants;
import org.graduate.common.core.redis.RedisCache;
import org.graduate.common.utils.uuid.IdUtils;
import org.graduate.framework.web.service.TokenService;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.annotation.Log;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.enums.BusinessType;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.system.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.graduate.system.service.IBStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.graduate.common.core.controller.BaseController;
import org.graduate.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import java.util.concurrent.TimeUnit;



@RestController
@RequestMapping("/api")
public class MobileApiController extends BaseController
{
    @Resource
    private IBPublicInfoService bPublicInfoService;
    @Autowired
    private IBStudentService bStudentService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private TokenService tokenService;
    @Resource
    private IBEmpinfoService bEmpinfoService;
    @Resource
    private IBCheckService ibCheckService;
    @Resource
    private IBCompanyService ibCompanyService;
    @Resource
    private IBSchoolService ibSchoolService;

    /**
     * 验证码接口
     */
    @Anonymous
    @GetMapping("/sendCode")
    public AjaxResult sendCode() throws Exception {
        try {
            // 保存验证码信息
            String uuid = IdUtils.simpleUUID();//生成uid
//        String code = MyClass.generateCode();//生成随机4位数验证码
            String code = "1234";
            String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
            //将verKey添加到redis
            redisCache.setCacheObject(verKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
//        SendSms.sendVerCode(code);//调用发送验证码
            //将验证码唯一标识符放入AjaxResult对象中。
            return AjaxResult.success("验证码发送成功").put("uuid", uuid);
        } catch (Exception e) {
            return AjaxResult.error("验证码发送失败");
        }
    }

    /**
     * 登录页面接口
     */
    @Anonymous
    @PostMapping("/logins")
    public AjaxResult logins(String phone, String code, String uuid) {
        String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
        Object cacheObject = redisCache.getCacheObject(verKey);//获取redis缓存的验证码

        // 去重电话号码和验证码
        String[] phones = phone.split(",");
        String[] codes = code.split(",");
        Set<String> phoneSet = new HashSet<>(Arrays.asList(phones));
        Set<String> codeSet = new HashSet<>(Arrays.asList(codes));
        phone = phoneSet.iterator().next();
        code = codeSet.iterator().next();

        // TODO: 在这里进行用户名和密码的校验操作
        String cacheString = cacheObject.toString();
        BStudent bStudent = bStudentService.selectBStudentPhone(phone);
        if (cacheString.equals(code) && bStudent != null) {
            String token = tokenService.createApiToken(bStudent);
            return AjaxResult.success("登录成功").put("token", token).put("bStudent", bStudent);
        } else {
            // 验证码或手机号错误
            return AjaxResult.success("手机号或验证码错误");
        }
    }

    //签到页面的渲染
    //@CrossOrigin允许跨域请求
    @CrossOrigin
    @Anonymous  //该注解取消权限控制
    @GetMapping("/getSignList")
    public TableDataInfo selectBCheck(BCheck bCheck) {
        startPage();
        List<BCheck> list = ibCheckService.selectBCheckList(bCheck);
        return getDataTable(list);
    }

    /**
     * 根据学生id查询学生信息
     * @param sId
     * @return
     */
    @GetMapping(value = "student/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return AjaxResult.success(bStudentService.selectBStudentBySId(sId));
    }
    /**
     * 发送手机验证码
     * @param
     * @return
     */
    @Anonymous
    @GetMapping("/sendPhoneCode")
    public AjaxResult sendCode(String phoneNum)
    {
        AjaxResult success = AjaxResult.success();
        /**
         *  1、生成uuid
         *  2、生成验证码 随机生成的4位数
         *  3、将 varifyKey添加到redis  varifyKey= 常量+uuid reids中验证码的唯一标识
         *  4、发送手机验证码
         */

        return null;
    }
    //存储经纬度
    @Anonymous  //该注解取消权限控制
    @PostMapping("/signin")
    public AjaxResult inputAddress(@RequestBody BCheck bCheck) {
        int i = ibCheckService.insertBCheck(bCheck);
        return AjaxResult.success(i);
    }
    //把经纬度发送到前端
    @Anonymous // 该注解取消权限控制
    @GetMapping("/locations")
    public AjaxResult getCompanyLocations() {
        List<Location> locations = ibCompanyService.selectBCompanyByPoint();
        return AjaxResult.success(locations);
    }
    /**
     * 就业信息页面接口
     **/
    @Anonymous
    @GetMapping("/selectEmpinfo")
    public TableDataInfo selectEmpinfo(BEmpinfo bEmpinfo) {
        startPage();
        List<BEmpinfo> list = bEmpinfoService.selectBEmpinfoList(bEmpinfo);
//        list = list.stream().filter(e -> e.getEmpStatus().equals("1")).collect(Collectors.toList());
        return getDataTable(list);
    }

    /**
     * 就业详情页面接口
     **/
    @Anonymous
    @GetMapping(value = "/showEmpinfoById")
    public AjaxResult showEmpinfoById(Long empId) {
        return AjaxResult.success(bEmpinfoService.selectBEmpinfoByEmpId(empId));
    }

    /**
     * 查询就业详情列表
     */

    @GetMapping("/searchEmpDetails")
    public AjaxResult selectEmpDetails(@RequestParam("keyword") String keyword) {
        BEmpinfo bEmpinfo = new BEmpinfo();
        bEmpinfo.setEmpName(keyword); //
        List<BEmpinfo> bEmpinfos = bEmpinfoService.selectBEmpinfoList(bEmpinfo);
        return AjaxResult.success(bEmpinfos);
    }

    /**
     * 获取公告数据的接口实现
     *
     * @return 返回公告数据的 JSON 格式字符串
     */
    @GetMapping("/announcement") // 使用@GetMapping注解，映射该方法到"/announcement"路径
    public TableDataInfo getAnnouncements() {
        // 调用 BPulicInfoService 的 selectBPublicInfoList 方法获取公告数据列表
        startPage();
        List<BPublicInfo> list = bPublicInfoService.selectBPublicInfoList(new BPublicInfo());
        return getDataTable(list);
    }



    /**
     * 查询公告管理列表
     */

    @GetMapping("/search")
    public AjaxResult selectAll(@RequestParam("keyword") String keyword) {
        BPublicInfo bPublicInfo = new BPublicInfo();
        bPublicInfo.setnContent(keyword); //
        List<BPublicInfo> bPublicInfos = bPublicInfoService.queryBPublicInfoList(bPublicInfo);
        return AjaxResult.success(bPublicInfos);
    }

    /**
     * 根据nID显示公告详情
     *
     * @param nId
     * @return
     */

    @GetMapping("/details")
    public AjaxResult selectById(Long nId) {
        return AjaxResult.success(bPublicInfoService.selectBPublicInfoByNId(nId));
    }

    /**
     * 柱形图-毕业信息
     */
    @Anonymous
    @GetMapping("/graduation")
    public AjaxResult selectGraduation() {
        List<Map<String, String>> data = bStudentService.selectBStudentCountMap();
        for (Map<String, String> map : data) {
                String schoolId = String.valueOf(map.get("school_id"));
                String abb = ibSchoolService.selectSchoolName(schoolId);
                String name = abb.replaceAll("职业技术", "");
                String Count = String.valueOf(map.get("employment_count"));
                // 将school_id替换为name
                map.put("name", name);
                map.remove("school_id");
                map.put("employment_count", Count);
        }
        // 在这里可以根据需要进行进一步处理
        return AjaxResult.success("调用成功").put("data",data);
    }

    /**
     * 折线图-人员变化一
     */
    @Anonymous
    @GetMapping("/Personnel")
    public AjaxResult selectPersonnel() {
        List<Map<String, String>> pes = bStudentService.selectBStudentTimeMap();
        for (Map<String, String> map : pes) {
            String month = String.valueOf(map.get("month"));
            String count = String.valueOf(map.get("count"));
            map.put("month", month);
            map.put("count", count);
        }
        // 在这里可以根据需要进行进一步处理
        return AjaxResult.success("调用成功").put("pes",pes);
    }

    /**
     * 折线图-人员变化二
     */
    @Anonymous
    @GetMapping("/PersonnelS")
    public AjaxResult selectPersonnelS() {
        List<Map<String, String>> peS = bStudentService.selectBStudentTimesMap();
        for (Map<String, String> map : peS) {
            String month = String.valueOf(map.get("month"));
            String count = String.valueOf(map.get("count"));
            map.put("month", month);
            map.put("count", count);
        }
        // 在这里可以根据需要进行进一步处理
        return AjaxResult.success("调用成功").put("pes",peS);
    }

    //柱状图
    /**
     * 柱状图
     *
     * @param
     * @return
     */


}




