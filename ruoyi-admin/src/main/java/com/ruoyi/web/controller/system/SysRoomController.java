package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.mapper.SysRoomMapper;
import com.ruoyi.system.service.ISysRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 宿舍房间号Controller
 * 
 * @author ruoyi
 * @date 2022-07-12
 */
@RestController
@RequestMapping("/system/room")
public class SysRoomController extends BaseController
{
    @Autowired
    private ISysRoomService sysRoomService;

    @Resource
    private SysRoomMapper sysRoomMapper;

    /**
     * 查询宿舍房间号列表
     */
    @PreAuthorize("@ss.hasPermi('system:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysRoom sysRoom) throws Exception {
        startPage();
        List<SysRoom> list = sysRoomService.selectSysRoomList(sysRoom);
        for (SysRoom room : list) {
            room.setRoomDept(
                    sysRoomMapper.searchDept(Integer.parseInt(room.getRoomDept()))
            );
        }
        return getDataTable(list);
    }

    /**
     * 导出宿舍房间号列表
     */
    @PreAuthorize("@ss.hasPermi('system:room:export')")
    @Log(title = "宿舍房间号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysRoom sysRoom) throws Exception {
        List<SysRoom> list = sysRoomService.selectSysRoomList(sysRoom);
        ExcelUtil<SysRoom> util = new ExcelUtil<SysRoom>(SysRoom.class);
        util.exportExcel(response, list, "宿舍房间号数据");
    }

    /**
     * 获取宿舍房间号详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:room:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return AjaxResult.success(sysRoomService.selectSysRoomByRoomId(roomId));
    }

    /**
     * 新增宿舍房间号
     */
    @PreAuthorize("@ss.hasPermi('system:room:add')")
    @Log(title = "宿舍房间号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysRoom sysRoom)
    {
        return toAjax(sysRoomService.insertSysRoom(sysRoom));
    }

    /**
     * 修改宿舍房间号
     */
    @PreAuthorize("@ss.hasPermi('system:room:edit')")
    @Log(title = "宿舍房间号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysRoom sysRoom)
    {
        return toAjax(sysRoomService.updateSysRoom(sysRoom));
    }

    /**
     * 删除宿舍房间号
     */
    @PreAuthorize("@ss.hasPermi('system:room:remove')")
    @Log(title = "宿舍房间号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(sysRoomService.deleteSysRoomByRoomIds(roomIds));
    }
}
