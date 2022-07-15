package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysGuest;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
@Repository
public interface SysGuestMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param guestId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysGuest selectSysGuestByGuestId(Long guestId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysGuest> selectSysGuestList(SysGuest sysGuest);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 结果
     */
    public int insertSysGuest(SysGuest sysGuest);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 结果
     */
    public int updateSysGuest(SysGuest sysGuest);

    /**
     * 删除【请填写功能名称】
     * 
     * @param guestId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysGuestByGuestId(Long guestId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param guestIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysGuestByGuestIds(Long[] guestIds);
}
