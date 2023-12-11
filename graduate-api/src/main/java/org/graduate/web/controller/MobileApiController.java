package org.graduate.web.controller;

import org.graduate.common.annotation.Anonymous;
import org.graduate.common.constant.CacheConstants;
import org.graduate.common.constant.Constants;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.core.redis.RedisCache;
import org.graduate.common.utils.uuid.IdUtils;
import org.graduate.framework.web.service.TokenService;
import org.graduate.system.domain.BStudent;
import org.graduate.common.annotation.Log;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.enums.BusinessType;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.system.domain.BCheck;
import org.graduate.system.domain.BTeacher;
import org.graduate.system.service.IBStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.graduate.system.service.IBCheckService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.graduate.common.core.controller.BaseController;
import org.graduate.common.core.page.TableDataInfo;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MobileApiController extends BaseController
{
    @Autowired
    private IBStudentService bStudentService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private TokenService tokenService;
    /**
     * 示例
     */
    //@Anonymous  该注解取消权限控制
    @Resource
    private IBCheckService ibCheckService;

    @Anonymous  //该注解取消权限控制
    @GetMapping("/list")
    public TableDataInfo list(BTeacher bTeacher)
    {
        return getDataTable(null);
    }

    /**
     * 验证码接口
     */
    @Anonymous
    @GetMapping("/sendCode")
    public  AjaxResult sendCode() throws Exception
    {
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
            return  AjaxResult.success("验证码发送成功").put("uuid", uuid);
        } catch (Exception e) {
            return AjaxResult.error("验证码发送失败");
        }
    }

    /**
     * 登录页面接口
     */
    @Anonymous
    @PostMapping("/logins")
    public AjaxResult logins(String phone , String code , String uuid)
    {
        String verKey = CacheConstants.PHONE_CODE_KEY + uuid;
        Object cacheObject = redisCache.getCacheObject(verKey);//获取redis缓存的验证码
        // TODO: 在这里进行用户名和密码的校验操作
        String cacheString = cacheObject.toString();
        BStudent bStudent = bStudentService.selectBStudentPhone(phone);
        if (cacheString.equals(code) && bStudent != null)
        {
            String token = tokenService.createApiToken(bStudent);
            return AjaxResult.success("登录成功").put("token",token).put("bStudent",bStudent);
        } else {
            // 验证码或手机号错误
            return AjaxResult.error("手机号或验证码错误");
        }
    }
    //签到页面的渲染
    //@CrossOrigin允许跨域请求
    @CrossOrigin
    @Anonymous  //该注解取消权限控制
    @GetMapping("/signin/getId")
    public AjaxResult selectBCheck(BCheck bCheck)
    {
        startPage();
        List<BCheck> list = ibCheckService.selectBCheckList(bCheck);
        return AjaxResult.success((list));
    }
//    //存储经纬度
//    @Anonymous  //该注解取消权限控制
//    @PostMapping("/signin")
//    public AjaxResult inputAddress(@RequestBody BCheck bCheck)
//    {
//      int i = ibCheckService.insertBCheck(bCheck);
//        return AjaxResult.success(i);
//    }

}
