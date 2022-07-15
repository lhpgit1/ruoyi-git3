package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysRoom;

import java.util.List;

/**
 * 宿舍房间号Service接口
 * 
 * @author ruoyi
 * @date 2022-07-12
 */
public interface ISysRoomService 
{
    /**
     * 查询宿舍房间号
     * 
     * @param roomId 宿舍房间号主键
     * @return 宿舍房间号
     */
    public SysRoom selectSysRoomByRoomId(Long roomId);

    /**
     * 查询宿舍房间号列表
     * 
     * @param sysRoom 宿舍房间号
     * @return 宿舍房间号集合
     */
    public List<SysRoom> selectSysRoomList(SysRoom sysRoom);

    /**
     * 新增宿舍房间号
     * 
     * @param sysRoom 宿舍房间号
     * @return 结果
     */
    public int insertSysRoom(SysRoom sysRoom);

    /**
     * 修改宿舍房间号
     * 
     * @param sysRoom 宿舍房间号
     * @return 结果
     */
    public int updateSysRoom(SysRoom sysRoom);

    /**
     * 批量删除宿舍房间号
     * 
     * @param roomIds 需要删除的宿舍房间号主键集合
     * @return 结果
     */
    public int deleteSysRoomByRoomIds(Long[] roomIds);

    /**
     * 删除宿舍房间号信息
     * 
     * @param roomId 宿舍房间号主键
     * @return 结果
     */
    public int deleteSysRoomByRoomId(Long roomId);
}
