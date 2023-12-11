package org.graduate.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import org.graduate.system.domain.BCheck;
import org.graduate.system.service.IBCheckService;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 签到Controller
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/system/check")
public class BCheckController extends BaseController
{
    @Autowired
    private IBCheckService bCheckService;

    /**
     * 查询签到列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(BCheck bCheck)
    {
        startPage();
        List<BCheck> list = bCheckService.selectBCheckList(bCheck);
        return getDataTable(list);
    }

    /**
     * 导出签到列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BCheck bCheck)
    {
        List<BCheck> list = bCheckService.selectBCheckList(bCheck);
        ExcelUtil<BCheck> util = new ExcelUtil<BCheck>(BCheck.class);
        util.exportExcel(response, list, "签到数据");
    }

    /**
     * 获取签到详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{ckId}")
    public AjaxResult getInfo(@PathVariable("ckId") Long ckId)
    {
        return AjaxResult.success(bCheckService.selectBCheckByCkId(ckId));
    }

    /**
     * 新增签到
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BCheck bCheck)
    {
        return toAjax(bCheckService.insertBCheck(bCheck));
    }

//    /**
//     * 修改签到
//     */
//    @PreAuthorize("@ss.hasPermi('system:check:edit')")
//    @Log(title = "签到", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody BCheck bCheck)
//    {
//        return toAjax(bCheckService.updateBCheck(bCheck));
//    }
//
//    /**
//     * 删除签到
//     */
//    @PreAuthorize("@ss.hasPermi('system:check:remove')")
//    @Log(title = "签到", businessType = BusinessType.DELETE)
//	@DeleteMapping("/{ckIds}")
//    public AjaxResult remove(@PathVariable Long[] ckIds)
//    {
//        return toAjax(bCheckService.deleteBCheckByCkIds(ckIds));
//    }
}
