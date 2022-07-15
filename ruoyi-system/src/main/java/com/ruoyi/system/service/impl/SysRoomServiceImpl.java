package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysRoom;
import com.ruoyi.system.mapper.SysRoomMapper;
import com.ruoyi.system.service.ISysRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宿舍房间号Service业务层处理
 *
 * @author ruoyi
 * @date 2022-07-12
 */
@Service
public class SysRoomServiceImpl implements ISysRoomService {
    private static final Map<String, String[]> dict = new HashMap<>();

    static {
        dict.put("101", new String[]{"103", "104", "105", "106", "107"});
        dict.put("102", new String[]{"108", "109", "200", "201"});
    }

    @Autowired
    private SysRoomMapper sysRoomMapper;

    /**
     * 查询宿舍房间号
     *
     * @param roomId 宿舍房间号主键
     * @return 宿舍房间号
     */
    @Override
    public SysRoom selectSysRoomByRoomId(Long roomId) {
        return sysRoomMapper.selectSysRoomByRoomId(roomId);
    }

    /**
     * 查询宿舍房间号列表
     *
     * @param sysRoom 宿舍房间号
     * @return 宿舍房间号
     */
    @Override
    public List<SysRoom> selectSysRoomList(SysRoom sysRoom) {
        //需要判空
        if(sysRoom.getRoomDept() != null){
            String dept = sysRoom.getRoomDept();
            if (dept.equals("100")) {
                return sysRoomMapper.selectSysRoomList(null);
            } else if (dict.containsKey(dept)) {
                List<SysRoom> list = new ArrayList<>();
                for (String s : dict.get(dept)) {
                    sysRoom.setRoomDept(s);
                    list.addAll(sysRoomMapper.selectSysRoomList(sysRoom));
                }
                return list;
            }
        }
            return sysRoomMapper.selectSysRoomList(sysRoom);
    }

    /**
     * 新增宿舍房间号
     *
     * @param sysRoom 宿舍房间号
     * @return 结果
     */
    @Override
    public int insertSysRoom(SysRoom sysRoom) {
        return sysRoomMapper.insertSysRoom(sysRoom);
    }

    /**
     * 修改宿舍房间号
     *
     * @param sysRoom 宿舍房间号
     * @return 结果
     */
    @Override
    public int updateSysRoom(SysRoom sysRoom) {
        return sysRoomMapper.updateSysRoom(sysRoom);
    }

    /**
     * 批量删除宿舍房间号
     *
     * @param roomIds 需要删除的宿舍房间号主键
     * @return 结果
     */
    @Override
    public int deleteSysRoomByRoomIds(Long[] roomIds) {
        return sysRoomMapper.deleteSysRoomByRoomIds(roomIds);
    }

    /**
     * 删除宿舍房间号信息
     *
     * @param roomId 宿舍房间号主键
     * @return 结果
     */
    @Override
    public int deleteSysRoomByRoomId(Long roomId) {
        return sysRoomMapper.deleteSysRoomByRoomId(roomId);
    }
}
