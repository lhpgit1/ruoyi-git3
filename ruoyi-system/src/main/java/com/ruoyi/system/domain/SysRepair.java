package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 【请填写功能名称】对象 sys_repair
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
public class SysRepair extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 维修Id */
    private Long repairId;

    /** 所属楼栋 */
    @Excel(name = "所属楼栋")
    private Long roomDept;

    /** 宿舍号 */
    @Excel(name = "宿舍号")
    private Long roomNumber;

    /** 宿舍长 */
    @Excel(name = "宿舍长")
    private String roomLeader;

    /** 宿舍联系电话 */
    @Excel(name = "宿舍联系电话")
    private String roomPhoneNumber;

    /** 维修内容 */
    @Excel(name = "维修内容")
    private String comment;

    /** 申报日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申报日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date repairDate;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setRepairId(Long repairId) 
    {
        this.repairId = repairId;
    }

    public Long getRepairId() 
    {
        return repairId;
    }
    public void setRoomDept(Long roomDept) 
    {
        this.roomDept = roomDept;
    }

    public Long getRoomDept() 
    {
        return roomDept;
    }
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }
    public void setRoomLeader(String roomLeader) 
    {
        this.roomLeader = roomLeader;
    }

    public String getRoomLeader() 
    {
        return roomLeader;
    }
    public void setRoomPhoneNumber(String roomPhoneNumber) 
    {
        this.roomPhoneNumber = roomPhoneNumber;
    }

    public String getRoomPhoneNumber() 
    {
        return roomPhoneNumber;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setRepairDate(Date repairDate) 
    {
        this.repairDate = repairDate;
    }

    public Date getRepairDate() 
    {
        return repairDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("repairId", getRepairId())
            .append("roomDept", getRoomDept())
            .append("roomNumber", getRoomNumber())
            .append("roomLeader", getRoomLeader())
            .append("roomPhoneNumber", getRoomPhoneNumber())
            .append("comment", getComment())
            .append("repairDate", getRepairDate())
            .append("status", getStatus())
            .toString();
    }
}
