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
import org.graduate.system.domain.BClass;
import org.graduate.system.service.IBClassService;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 班级管理Controller
 * 
 * @author chuan
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/system/class")
public class BClassController extends BaseController
{
    @Autowired
    private IBClassService b_ClassService;

    /**
     * 查询班级管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(BClass b_Class)
    {
        startPage();
        List<BClass> list = b_ClassService.selectB_ClassList(b_Class);
        return getDataTable(list);
    }

    /**
     * 导出班级管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:export')")
    @Log(title = "班级管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BClass b_Class)
    {
        List<BClass> list = b_ClassService.selectB_ClassList(b_Class);
        ExcelUtil<BClass> util = new ExcelUtil<BClass>(BClass.class);
        util.exportExcel(response, list, "班级管理数据");
    }

    /**
     * 获取班级管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:query')")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId)
    {
        return AjaxResult.success(b_ClassService.selectB_ClassByCId(cId));
    }

    /**
     * 新增班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:class:add')")
    @Log(title = "班级管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BClass b_Class)
    {
        return toAjax(b_ClassService.insertB_Class(b_Class));
    }

    /**
     * 修改班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:class:edit')")
    @Log(title = "班级管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BClass b_Class)
    {
        return toAjax(b_ClassService.updateB_Class(b_Class));
    }

    /**
     * 删除班级管理
     */
    @PreAuthorize("@ss.hasPermi('system:class:remove')")
    @Log(title = "班级管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds)
    {
        return toAjax(b_ClassService.deleteB_ClassByCIds(cIds));
    }
}
