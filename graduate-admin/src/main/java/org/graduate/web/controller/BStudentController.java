package org.graduate.web.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import org.graduate.common.utils.StudentNumberGenerator;
import org.graduate.system.service.*;
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
import org.graduate.system.domain.BStudent;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 学生管理Controller
 * 
 * @author chuan
 * @date 2023-11-24
 */
@RestController
@RequestMapping("/system/student")
public class BStudentController extends BaseController
{
    @Autowired
    private IBStudentService bStudentService;
    @Autowired
    private IBCompanyService iBCompanyService;
    @Autowired
    private IBTeacherService ibTeacherService;
    @Autowired
    private IBClassService ibClassService;
    @Autowired
    private IBSchoolService ibSchoolService;

    /**
     * 查询学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(BStudent bStudent)
    {
        startPage();
        List<BStudent> list = bStudentService.selectBStudentList(bStudent);
        return getDataTable(list);
    }

    /**
     * 导出学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:student:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BStudent bStudent)
    {
        List<BStudent> list = bStudentService.selectBStudentList(bStudent);
        ExcelUtil<BStudent> util = new ExcelUtil<BStudent>(BStudent.class);
        util.exportExcel(response, list, "学生管理数据");
    }

    /**
     * 获取学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:student:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return AjaxResult.success(bStudentService.selectBStudentBySId(sId));
    }

    /**
     * 新增学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BStudent bStudent)
    {
        bStudent.setsNumber(StudentNumberGenerator.generateStudentNumber());
        return toAjax(bStudentService.insertBStudent(bStudent));
    }

    /**
     * 修改学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BStudent bStudent)
    {
        return toAjax(bStudentService.updateBStudent(bStudent));
    }

    /**
     * 删除学生管理
     */
    @PreAuthorize("@ss.hasPermi('system:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(bStudentService.deleteBStudentBySIds(sIds));
    }

    @GetMapping(value = "/initStudent")
    public AjaxResult initStudent()
    {
        AjaxResult success = AjaxResult.success();
        success.put("companys",iBCompanyService.selectBCompanyAll());
        success.put("teachers",ibTeacherService.selectBTeacherAll());
        success.put("clasei",ibClassService.selectBClassAll());
        success.put("schools",ibSchoolService.selectBSchoolAll());
        return success;
    }
}
