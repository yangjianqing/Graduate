package org.graduate.web.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.servlet.http.HttpServletResponse;

import org.graduate.common.utils.MailUtil;
import org.graduate.system.service.IBStudentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.graduate.common.annotation.Log;
import org.graduate.common.core.controller.BaseController;
import org.graduate.common.core.domain.AjaxResult;
import org.graduate.common.enums.BusinessType;
import org.graduate.system.domain.BPublicInfo;
import org.graduate.system.service.IBPublicInfoService;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 公告管理Controller
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@RestController
@RequestMapping("/system/info")
public class BPublicInfoController extends BaseController
{
    @Autowired
    private IBPublicInfoService bPublicInfoService;
    @Autowired
    private IBStudentService studentService;

    /**
     * 查询公告管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(BPublicInfo bPublicInfo)
    {
        startPage();
        List<BPublicInfo> list = bPublicInfoService.selectBPublicInfoList(bPublicInfo);
        return getDataTable(list);
    }

    /**
     * 导出公告管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:info:export')")
    @Log(title = "公告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BPublicInfo bPublicInfo)
    {
        List<BPublicInfo> list = bPublicInfoService.selectBPublicInfoList(bPublicInfo);
        ExcelUtil<BPublicInfo> util = new ExcelUtil<BPublicInfo>(BPublicInfo.class);
        util.exportExcel(response, list, "公告管理数据");
    }

    /**
     * 获取公告管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:info:query')")
    @GetMapping(value = "/{nId}")
    public AjaxResult getInfo(@PathVariable("nId") Long nId)
    {
        return AjaxResult.success(bPublicInfoService.selectBPublicInfoByNId(nId));
    }

    /**
     * 新增公告管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:add')")
    @Log(title = "公告管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BPublicInfo bPublicInfo) {
        CompletableFuture.runAsync(() -> {
            List<String> strings = studentService.AllMailbox();
            try {
                MailUtil.batchSendTextMail(bPublicInfo.getnTitle(), bPublicInfo.getnContent(),strings);
                } catch (Exception e) {
                // 其他异常处理
                e.printStackTrace();
            }
        });
        return toAjax(bPublicInfoService.insertBPublicInfo(bPublicInfo));
    }



    /**
     * 修改公告管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:edit')")
    @Log(title = "公告管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BPublicInfo bPublicInfo)
    {
        return toAjax(bPublicInfoService.updateBPublicInfo(bPublicInfo));
    }

    /**
     * 删除公告管理
     */
    @PreAuthorize("@ss.hasPermi('system:info:remove')")
    @Log(title = "公告管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{nIds}")
    public AjaxResult remove(@PathVariable Long[] nIds)
    {
        return toAjax(bPublicInfoService.deleteBPublicInfoByNIds(nIds));
    }
}
