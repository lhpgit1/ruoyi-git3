package com.ruoyi.web.controller.system;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysLate;
import com.ruoyi.system.service.ISysLateService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 夜归管理Controller
 * 
 * @author ruoyi
 * @date 2022-07-14
 */
@RestController
@RequestMapping("/system/late")
public class SysLateController extends BaseController
{
    @Autowired
    private ISysLateService sysLateService;

    /**
     * 查询夜归管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:late:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLate sysLate)
    {
        startPage();
        List<SysLate> list = sysLateService.selectSysLateList(sysLate);
        return getDataTable(list);
    }

    /**
     * 导出夜归管理列表
     */
    @PreAuthorize("@ss.hasPermi('system:late:export')")
    @Log(title = "夜归管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLate sysLate)
    {
        List<SysLate> list = sysLateService.selectSysLateList(sysLate);
        ExcelUtil<SysLate> util = new ExcelUtil<SysLate>(SysLate.class);
        util.exportExcel(response, list, "夜归管理数据");
    }

    /**
     * 获取夜归管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:late:query')")
    @GetMapping(value = "/{lateId}")
    public AjaxResult getInfo(@PathVariable("lateId") Long lateId)
    {
        return AjaxResult.success(sysLateService.selectSysLateByLateId(lateId));
    }

    /**
     * 新增夜归管理
     */
    @PreAuthorize("@ss.hasPermi('system:late:add')")
    @Log(title = "夜归管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysLate sysLate)
    {
        return toAjax(sysLateService.insertSysLate(sysLate));
    }

    /**
     * 修改夜归管理
     */
    @PreAuthorize("@ss.hasPermi('system:late:edit')")
    @Log(title = "夜归管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysLate sysLate)
    {
        return toAjax(sysLateService.updateSysLate(sysLate));
    }

    /**
     * 删除夜归管理
     */
    @PreAuthorize("@ss.hasPermi('system:late:remove')")
    @Log(title = "夜归管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{lateIds}")
    public AjaxResult remove(@PathVariable Long[] lateIds)
    {
        return toAjax(sysLateService.deleteSysLateByLateIds(lateIds));
    }
}
