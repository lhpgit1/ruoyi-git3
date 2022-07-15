package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysRepair;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
public interface SysRepairMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param repairId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public SysRepair selectSysRepairByRepairId(Long repairId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysRepair> selectSysRepairList(SysRepair sysRepair);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 结果
     */
    public int insertSysRepair(SysRepair sysRepair);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 结果
     */
    public int updateSysRepair(SysRepair sysRepair);

    /**
     * 删除【请填写功能名称】
     * 
     * @param repairId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSysRepairByRepairId(Long repairId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param repairIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysRepairByRepairIds(Long[] repairIds);
}
