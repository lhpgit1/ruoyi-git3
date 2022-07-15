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
import com.ruoyi.system.domain.SysGuest;
import com.ruoyi.system.service.ISysGuestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
@RestController
@RequestMapping("/system/guest")
public class SysGuestController extends BaseController
{
    @Autowired
    private ISysGuestService sysGuestService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:guest:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysGuest sysGuest)
    {
        startPage();
        List<SysGuest> list = sysGuestService.selectSysGuestList(sysGuest);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:guest:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysGuest sysGuest)
    {
        List<SysGuest> list = sysGuestService.selectSysGuestList(sysGuest);
        ExcelUtil<SysGuest> util = new ExcelUtil<SysGuest>(SysGuest.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:guest:query')")
    @GetMapping(value = "/{guestId}")
    public AjaxResult getInfo(@PathVariable("guestId") Long guestId)
    {
        return AjaxResult.success(sysGuestService.selectSysGuestByGuestId(guestId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:guest:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysGuest sysGuest)
    {
        return toAjax(sysGuestService.insertSysGuest(sysGuest));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:guest:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysGuest sysGuest)
    {
        return toAjax(sysGuestService.updateSysGuest(sysGuest));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:guest:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{guestIds}")
    public AjaxResult remove(@PathVariable Long[] guestIds)
    {
        return toAjax(sysGuestService.deleteSysGuestByGuestIds(guestIds));
    }
}
