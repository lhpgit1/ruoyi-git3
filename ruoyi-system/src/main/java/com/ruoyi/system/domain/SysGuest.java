package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 sys_guest
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
public class SysGuest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 来访记录号 */
    private Long guestId;

    /** 来访人姓名 */
    @Excel(name = "来访人姓名")
    private String guestName;

    /** 来访人手机号 */
    @Excel(name = "来访人手机号")
    private String guestPhoneNumber;

    /** 来访时间记录 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "来访时间记录", width = 30, dateFormat = "yyyy-MM-dd")
    private Date guestTime;

    /** 访问原由说明 */
    @Excel(name = "访问原由说明")
    private String guestExplain;

    /** 访问去向楼栋 */
    @Excel(name = "访问去向楼栋")
    private String guestDept;

    /** 访问去向宿舍号 */
    @Excel(name = "访问去向宿舍号")
    private Long guestRoom;

    public void setGuestId(Long guestId) 
    {
        this.guestId = guestId;
    }

    public Long getGuestId() 
    {
        return guestId;
    }
    public void setGuestName(String guestName) 
    {
        this.guestName = guestName;
    }

    public String getGuestName() 
    {
        return guestName;
    }
    public void setGuestPhoneNumber(String guestPhoneNumber) 
    {
        this.guestPhoneNumber = guestPhoneNumber;
    }

    public String getGuestPhoneNumber() 
    {
        return guestPhoneNumber;
    }
    public void setGuestTime(Date guestTime) 
    {
        this.guestTime = guestTime;
    }

    public Date getGuestTime() 
    {
        return guestTime;
    }
    public void setGuestExplain(String guestExplain) 
    {
        this.guestExplain = guestExplain;
    }

    public String getGuestExplain() 
    {
        return guestExplain;
    }
    public void setGuestDept(String guestDept) 
    {
        this.guestDept = guestDept;
    }

    public String getGuestDept() 
    {
        return guestDept;
    }
    public void setGuestRoom(Long guestRoom) 
    {
        this.guestRoom = guestRoom;
    }

    public Long getGuestRoom() 
    {
        return guestRoom;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("guestId", getGuestId())
            .append("guestName", getGuestName())
            .append("guestPhoneNumber", getGuestPhoneNumber())
            .append("guestTime", getGuestTime())
            .append("guestExplain", getGuestExplain())
            .append("guestDept", getGuestDept())
            .append("guestRoom", getGuestRoom())
            .toString();
    }
}
