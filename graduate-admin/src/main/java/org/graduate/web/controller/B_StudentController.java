package org.graduate.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.graduate.system.domain.B_Student;
import org.graduate.system.service.IB_StudentService;
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
 * 学生管理Controller
 * 
 * @author chuan
 * @date 2023-11-21
 */
@RestController
@RequestMapping("/system/student")
public class B_StudentController extends BaseController
{
    @Autowired
    private IB_StudentService b_StudentService;

    /**
     * 查询学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(B_Student b_Student)
    {
        startPage();
        List<B_Student> list = b_StudentService.selectB_StudentList(b_Student);
        return getDataTable(list);
    }

    /**
     * 导出学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, B_Student b_Student)
    {
        List<B_Student> list = b_StudentService.selectB_StudentList(b_Student);
        ExcelUtil<B_Student> util = new ExcelUtil<B_Student>(B_Student.class);
        util.exportExcel(response, list, "学生管理数据");
    }

    /**
     * 获取学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return AjaxResult.success(b_StudentService.selectB_StudentBySId(sId));
    }

    /**
     * 新增学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody B_Student b_Student)
    {
        return toAjax(b_StudentService.insertB_Student(b_Student));
    }

    /**
     * 修改学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody B_Student b_Student)
    {
        return toAjax(b_StudentService.updateB_Student(b_Student));
    }

    /**
     * 删除学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(b_StudentService.deleteB_StudentBySIds(sIds));
    }
}
