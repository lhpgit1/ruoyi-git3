package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 夜归管理对象 sys_late
 * 
 * @author ruoyi
 * @date 2022-07-14
 */
public class SysLate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 夜归记录号 */
    private Long lateId;

    /** 学号 */
    @Excel(name = "学号")
    private Long lateStuId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String lateStuName;

    /** 夜归原由 */
    @Excel(name = "夜归原由")
    private String lateReason;

    /** 楼栋名 */
    @Excel(name = "楼栋名")
    private String lateDeptName;

    /** 宿舍号 */
    @Excel(name = "宿舍号")
    private Long lateRoomId;

    /** 夜归日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "夜归日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lateDate;

    public void setLateId(Long lateId) 
    {
        this.lateId = lateId;
    }

    public Long getLateId() 
    {
        return lateId;
    }
    public void setLateStuId(Long lateStuId) 
    {
        this.lateStuId = lateStuId;
    }

    public Long getLateStuId() 
    {
        return lateStuId;
    }
    public void setLateStuName(String lateStuName) 
    {
        this.lateStuName = lateStuName;
    }

    public String getLateStuName() 
    {
        return lateStuName;
    }
    public void setLateReason(String lateReason) 
    {
        this.lateReason = lateReason;
    }

    public String getLateReason() 
    {
        return lateReason;
    }
    public void setLateDeptName(String lateDeptName) 
    {
        this.lateDeptName = lateDeptName;
    }

    public String getLateDeptName() 
    {
        return lateDeptName;
    }
    public void setLateRoomId(Long lateRoomId) 
    {
        this.lateRoomId = lateRoomId;
    }

    public Long getLateRoomId() 
    {
        return lateRoomId;
    }
    public void setLateDate(Date lateDate) 
    {
        this.lateDate = lateDate;
    }

    public Date getLateDate() 
    {
        return lateDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("lateId", getLateId())
            .append("lateStuId", getLateStuId())
            .append("lateStuName", getLateStuName())
            .append("lateReason", getLateReason())
            .append("lateDeptName", getLateDeptName())
            .append("lateRoomId", getLateRoomId())
            .append("lateDate", getLateDate())
            .toString();
    }
}
