package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 宿舍房间号对象 sys_room
 * 
 * @author ruoyi
 * @date 2022-07-12
 */
public class SysRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long roomId;

    /** $column.columnComment */
    @Excel(name = "房号", readConverterExp = "$column.readConverterExp()")
    private Long roomNumber;

    /** $column.columnComment */
    @Excel(name = "楼栋号", readConverterExp = "$column.readConverterExp()")
    private String roomDept;

    /** $column.columnComment */
    @Excel(name = "人数", readConverterExp = "$column.readConverterExp()")
    private Long roomPeople;

    /** $column.columnComment */
    @Excel(name = "宿舍长", readConverterExp = "$column.readConverterExp()")
    private String roomLeader;

    /** $column.columnComment */
    @Excel(name = "联系电话", readConverterExp = "$column.readConverterExp()")
    private String roomPhoneNumber;


    public void setRoomId(Long roomId) 
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }
    public void setRoomNumber(Long roomNumber) 
    {
        this.roomNumber = roomNumber;
    }

    public Long getRoomNumber() 
    {
        return roomNumber;
    }
    public void setRoomDept(String roomDept) 
    {
        this.roomDept = roomDept;
    }

    public String getRoomDept() 
    {
        return roomDept;
    }
    public void setRoomPeople(Long roomPeople) 
    {
        this.roomPeople = roomPeople;
    }

    public Long getRoomPeople() 
    {
        return roomPeople;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("roomNumber", getRoomNumber())
            .append("roomDept", getRoomDept())
            .append("roomPeople", getRoomPeople())
            .append("roomLeader", getRoomLeader())
            .append("roomPhoneNumber", getRoomPhoneNumber())
            .toString();
    }
}
