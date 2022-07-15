package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysRepair;
import com.ruoyi.system.mapper.SysRepairMapper;
import com.ruoyi.system.service.ISysRepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
@Service
public class SysRepairServiceImpl implements ISysRepairService
{
    @Autowired
    private SysRepairMapper sysRepairMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param repairId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysRepair selectSysRepairByRepairId(Long repairId)
    {
        return sysRepairMapper.selectSysRepairByRepairId(repairId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysRepair> selectSysRepairList(SysRepair sysRepair)
    {
        return sysRepairMapper.selectSysRepairList(sysRepair);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysRepair(SysRepair sysRepair)
    {
        return sysRepairMapper.insertSysRepair(sysRepair);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sysRepair 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysRepair(SysRepair sysRepair)
    {
        return sysRepairMapper.updateSysRepair(sysRepair);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param repairIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRepairByRepairIds(Long[] repairIds)
    {
        return sysRepairMapper.deleteSysRepairByRepairIds(repairIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param repairId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysRepairByRepairId(Long repairId)
    {
        return sysRepairMapper.deleteSysRepairByRepairId(repairId);
    }
}
