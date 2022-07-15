package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysRoom;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 宿舍房间号Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-12
 */
public interface SysRoomMapper 
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
     * 删除宿舍房间号
     * 
     * @param roomId 宿舍房间号主键
     * @return 结果
     */
    public int deleteSysRoomByRoomId(Long roomId);

    /**
     * 批量删除宿舍房间号
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysRoomByRoomIds(Long[] roomIds);

    @Select("select dept_name from sys_dept where dept_id = #{deptId}")
    public String searchDept(int deptId);
}
