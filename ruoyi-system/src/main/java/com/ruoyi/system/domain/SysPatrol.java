package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 巡查管理对象 sys_patrol
 * 
 * @author ruoyi
 * @date 2022-07-14
 */
public class SysPatrol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 巡查记录号 */
    private Long patrolId;

    /** 巡查人员ID */
    @Excel(name = "巡查人员ID")
    private Long patrolStaffId;

    /** 巡查人姓名 */
    @Excel(name = "巡查人姓名")
    private String patrolName;

    /** 巡查时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "巡查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date patrolTime;

    /** 巡查楼栋 */
    @Excel(name = "巡查楼栋")
    private String patrolDept;

    /** 巡查原由 */
    @Excel(name = "巡查原由")
    private String patrolMatter;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String patrolPhoneNumber;

    public void setPatrolId(Long patrolId)
    {
        this.patrolId = patrolId;
    }

    public Long getPatrolId()
    {
        return patrolId;
    }
    public void setPatrolStaffId(Long patrolStaffId)
    {
        this.patrolStaffId = patrolStaffId;
    }

    public Long getPatrolStaffId()
    {
        return patrolStaffId;
    }
    public void setPatrolName(String patrolName)
    {
        this.patrolName = patrolName;
    }

    public String getPatrolName()
    {
        return patrolName;
    }
    public void setPatrolTime(Date patrolTime)
    {
        this.patrolTime = patrolTime;
    }

    public Date getPatrolTime()
    {
        return patrolTime;
    }
    public void setPatrolDept(String patrolDept)
    {
        this.patrolDept = patrolDept;
    }

    public String getPatrolDept()
    {
        return patrolDept;
    }
    public void setPatrolMatter(String patrolMatter)
    {
        this.patrolMatter = patrolMatter;
    }

    public String getPatrolMatter()
    {
        return patrolMatter;
    }
    public void setPatrolPhoneNumber(String patrolPhoneNumber)
    {
        this.patrolPhoneNumber = patrolPhoneNumber;
    }

    public String getPatrolPhoneNumber()
    {
        return patrolPhoneNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("patrolId", getPatrolId())
            .append("patrolStaffId", getPatrolStaffId())
            .append("patrolName", getPatrolName())
            .append("patrolTime", getPatrolTime())
            .append("patrolDept", getPatrolDept())
            .append("patrolMatter", getPatrolMatter())
            .append("patrolPhoneNumber", getPatrolPhoneNumber())
            .toString();
    }
}
