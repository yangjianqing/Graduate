package org.graduate.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.graduate.system.domain.BSchool;
import org.graduate.system.service.IBSchoolService;
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
 * 学校管理Controller
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@RestController
@RequestMapping("/system/school")
public class BSchoolController extends BaseController
{
    @Autowired
    private IBSchoolService bSchoolService;

    /**
     * 查询学校管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:list')")
    @GetMapping("/list")
    public TableDataInfo list(BSchool bSchool)
    {
        startPage();
        List<BSchool> list = bSchoolService.selectBSchoolList(bSchool);
        return getDataTable(list);
    }

    /**
     * 导出学校管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:school:export')")
    @Log(title = "学校管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BSchool bSchool)
    {
        List<BSchool> list = bSchoolService.selectBSchoolList(bSchool);
        ExcelUtil<BSchool> util = new ExcelUtil<BSchool>(BSchool.class);
        util.exportExcel(response, list, "学校管理数据");
    }

    /**
     * 获取学校管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:school:query')")
    @GetMapping(value = "/{sId}")
    public AjaxResult getInfo(@PathVariable("sId") Long sId)
    {
        return AjaxResult.success(bSchoolService.selectBSchoolBySId(sId));
    }

    /**
     * 新增学校管理
     */
    @PreAuthorize("@ss.hasPermi('system:school:add')")
    @Log(title = "学校管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BSchool bSchool)
    {
        return toAjax(bSchoolService.insertBSchool(bSchool));
    }

    /**
     * 修改学校管理
     */
    @PreAuthorize("@ss.hasPermi('system:school:edit')")
    @Log(title = "学校管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BSchool bSchool)
    {
        return toAjax(bSchoolService.updateBSchool(bSchool));
    }

    /**
     * 删除学校管理
     */
    @PreAuthorize("@ss.hasPermi('system:school:remove')")
    @Log(title = "学校管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sIds}")
    public AjaxResult remove(@PathVariable Long[] sIds)
    {
        return toAjax(bSchoolService.deleteBSchoolBySIds(sIds));
    }
}
