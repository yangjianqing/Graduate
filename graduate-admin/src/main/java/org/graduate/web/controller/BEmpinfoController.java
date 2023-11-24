package org.graduate.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.graduate.system.domain.BEmpinfo;
import org.graduate.system.service.IBEmpinfoService;
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
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 就业信息发布Controller
 *
 * @author ruoyi
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/system/empinfo")
public class BEmpinfoController extends BaseController
{
    @Autowired
    private IBEmpinfoService bEmpinfoService;

    /**
     * 查询就业信息发布列表
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(BEmpinfo bEmpinfo)
    {
        startPage();
        List<BEmpinfo> list = bEmpinfoService.selectBEmpinfoList(bEmpinfo);
        return getDataTable(list);
    }

    /**
     * 导出就业信息发布列表
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:export')")
    @Log(title = "就业信息发布", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BEmpinfo bEmpinfo)
    {
        List<BEmpinfo> list = bEmpinfoService.selectBEmpinfoList(bEmpinfo);
        ExcelUtil<BEmpinfo> util = new ExcelUtil<BEmpinfo>(BEmpinfo.class);
        util.exportExcel(response, list, "就业信息发布数据");
    }

    /**
     * 获取就业信息发布详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:query')")
    @GetMapping(value = "/{empId}")
    public AjaxResult getInfo(@PathVariable("empId") Long empId)
    {
        return AjaxResult.success(bEmpinfoService.selectBEmpinfoByEmpId(empId));
    }

    /**
     * 新增就业信息发布
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:add')")
    @Log(title = "就业信息发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BEmpinfo bEmpinfo)
    {
        return toAjax(bEmpinfoService.insertBEmpinfo(bEmpinfo));
    }

    /**
     * 修改就业信息发布
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:edit')")
    @Log(title = "就业信息发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BEmpinfo bEmpinfo)
    {
        return toAjax(bEmpinfoService.updateBEmpinfo(bEmpinfo));
    }

    /**
     * 删除就业信息发布
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:remove')")
    @Log(title = "就业信息发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{empIds}")
    public AjaxResult remove(@PathVariable Long[] empIds)
    {
        return toAjax(bEmpinfoService.deleteBEmpinfoByEmpIds(empIds));
    }

    /**
     * 调整就业信息发布状态
     */
    @PreAuthorize("@ss.hasPermi('system:empinfo:modified')")
    @Log(title = "就业信息发布", businessType = BusinessType.UPDATE)
    @PutMapping("/{empId}/{empStatus}")
    public AjaxResult modified(@PathVariable Long empId, @PathVariable String empStatus)
    {
        BEmpinfo empinfo = new BEmpinfo();
        empinfo.setEmpId(empId);
        empinfo.setEmpStatus(empStatus);
        return toAjax(bEmpinfoService.modifiedBEmpinfoByEmpId(empinfo));
    }
}
