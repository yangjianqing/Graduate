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
import org.graduate.system.domain.BCompany;
import org.graduate.system.service.IBCompanyService;
import org.graduate.common.utils.poi.ExcelUtil;
import org.graduate.common.core.page.TableDataInfo;

/**
 * 公司管理Controller
 * 
 * @author ruoyi
 * @date 2023-11-22
 */
@RestController
@RequestMapping("/system/company")
public class BCompanyController extends BaseController
{
    @Autowired
    private IBCompanyService bCompanyService;

    /**
     * 查询公司管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(BCompany bCompany)
    {
        startPage();
        List<BCompany> list = bCompanyService.selectBCompanyList(bCompany);
        return getDataTable(list);
    }

    /**
     * 导出公司管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:company:export')")
    @Log(title = "公司管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BCompany bCompany)
    {
        List<BCompany> list = bCompanyService.selectBCompanyList(bCompany);
        ExcelUtil<BCompany> util = new ExcelUtil<BCompany>(BCompany.class);
        util.exportExcel(response, list, "公司管理数据");
    }

    /**
     * 获取公司管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:company:query')")
    @GetMapping(value = "/{cId}")
    public AjaxResult getInfo(@PathVariable("cId") Long cId)
    {
        return AjaxResult.success(bCompanyService.selectBCompanyByCId(cId));
    }

    /**
     * 新增公司管理
     */
    @PreAuthorize("@ss.hasPermi('system:company:add')")
    @Log(title = "公司管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BCompany bCompany)
    {
        return toAjax(bCompanyService.insertBCompany(bCompany));
    }

    /**
     * 修改公司管理
     */
    @PreAuthorize("@ss.hasPermi('system:company:edit')")
    @Log(title = "公司管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BCompany bCompany)
    {
        return toAjax(bCompanyService.updateBCompany(bCompany));
    }

    /**
     * 删除公司管理
     */
    @PreAuthorize("@ss.hasPermi('system:company:remove')")
    @Log(title = "公司管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{cIds}")
    public AjaxResult remove(@PathVariable Long[] cIds)
    {
        return toAjax(bCompanyService.deleteBCompanyByCIds(cIds));
    }
}
