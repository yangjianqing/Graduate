package org.graduate.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.graduate.system.domain.BTeacher;
import org.graduate.system.service.IBTeacherService;
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
 * 教师信息Controller
 * 
 * @author ruoyi
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/system/teacher")
public class BTeacherController extends BaseController
{
    @Autowired
    private IBTeacherService bTeacherService;

    /**
     * 查询教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(BTeacher bTeacher)
    {
        startPage();
        List<BTeacher> list = bTeacherService.selectBTeacherList(bTeacher);
        return getDataTable(list);
    }

    /**
     * 导出教师信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:export')")
    @Log(title = "教师信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BTeacher bTeacher)
    {
        List<BTeacher> list = bTeacherService.selectBTeacherList(bTeacher);
        ExcelUtil<BTeacher> util = new ExcelUtil<BTeacher>(BTeacher.class);
        util.exportExcel(response, list, "教师信息数据");
    }

    /**
     * 获取教师信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:query')")
    @GetMapping(value = "/{tchrId}")
    public AjaxResult getInfo(@PathVariable("tchrId") Long tchrId)
    {
        return AjaxResult.success(bTeacherService.selectBTeacherByTchrId(tchrId));
    }

    /**
     * 新增教师信息
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:add')")
    @Log(title = "教师信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BTeacher bTeacher)
    {
        return toAjax(bTeacherService.insertBTeacher(bTeacher));
    }

    /**
     * 修改教师信息
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:edit')")
    @Log(title = "教师信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BTeacher bTeacher)
    {
        return toAjax(bTeacherService.updateBTeacher(bTeacher));
    }

    /**
     * 删除教师信息
     */
    @PreAuthorize("@ss.hasPermi('system:teacher:remove')")
    @Log(title = "教师信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tchrIds}")
    public AjaxResult remove(@PathVariable Long[] tchrIds)
    {
        return toAjax(bTeacherService.deleteBTeacherByTchrIds(tchrIds));
    }
}
