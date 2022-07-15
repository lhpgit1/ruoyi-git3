package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysPatrol;

import java.util.List;

/**
 * 巡查管理Mapper接口
 * 
 * @author ruoyi
 * @date 2022-07-14
 */
public interface SysPatrolMapper 
{
    /**
     * 查询巡查管理
     * 
     * @param patrolId 巡查管理主键
     * @return 巡查管理
     */
    public SysPatrol selectSysPatrolByPatrolId(Long patrolId);

    /**
     * 查询巡查管理列表
     * 
     * @param sysPatrol 巡查管理
     * @return 巡查管理集合
     */
    public List<SysPatrol> selectSysPatrolList(SysPatrol sysPatrol);

    /**
     * 新增巡查管理
     * 
     * @param sysPatrol 巡查管理
     * @return 结果
     */
    public int insertSysPatrol(SysPatrol sysPatrol);

    /**
     * 修改巡查管理
     * 
     * @param sysPatrol 巡查管理
     * @return 结果
     */
    public int updateSysPatrol(SysPatrol sysPatrol);

    /**
     * 删除巡查管理
     * 
     * @param patrolId 巡查管理主键
     * @return 结果
     */
    public int deleteSysPatrolByPatrolId(Long patrolId);

    /**
     * 批量删除巡查管理
     * 
     * @param patrolIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysPatrolByPatrolIds(Long[] patrolIds);
}
