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
 * 班级Controller
 * 
 * @author ruoyi
 * @date 2023-11-20
 */
@RestController
@RequestMapping("/system/class")
public class BClassController extends BaseController
{
    @Autowired
    private IBClassService bClassService;

    /**
     * 查询班级列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(BClass bClass)
    {
        startPage();
        List<BClass> list = bClassService.selectBClassList(bClass);
        return getDataTable(list);
    }

    /**
     * 导出班级列表
     */
    @PreAuthorize("@ss.hasPermi('system:class:export')")
    @Log(title = "班级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BClass bClass)
    {
        List<BClass> list = bClassService.selectBClassList(bClass);
        ExcelUtil<BClass> util = new ExcelUtil<BClass>(BClass.class);
        util.exportExcel(response, list, "班级数据");
    }

    /**
     * 获取班级详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:class:query')")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId)
    {
        return AjaxResult.success(bClassService.selectBClassByCId(cId));
    }

    /**
     * 新增班级
     */
    @PreAuthorize("@ss.hasPermi('system:class:add')")
    @Log(title = "班级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BClass bClass)
    {
        return toAjax(bClassService.insertBClass(bClass));
    }

    /**
     * 修改班级
     */
    @PreAuthorize("@ss.hasPermi('system:class:edit')")
    @Log(title = "班级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BClass bClass)
    {
        return toAjax(bClassService.updateBClass(bClass));
    }

    /**
     * 删除班级
     */
    @PreAuthorize("@ss.hasPermi('system:class:remove')")
    @Log(title = "班级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds)
    {
        return toAjax(bClassService.deleteBClassByCIds(cIds));
    }
}
