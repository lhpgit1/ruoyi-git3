package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysGuestMapper;
import com.ruoyi.system.domain.SysGuest;
import com.ruoyi.system.service.ISysGuestService;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-13
 */
@Service
public class SysGuestServiceImpl extends Exception implements ISysGuestService
{
    @Autowired
    private SysGuestMapper sysGuestMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysRoomMapper sysRoomMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param guestId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public SysGuest selectSysGuestByGuestId(Long guestId)
    {
        return sysGuestMapper.selectSysGuestByGuestId(guestId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysGuest> selectSysGuestList(SysGuest sysGuest)
    {
        return sysGuestMapper.selectSysGuestList(sysGuest);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 结果
     */
    static final long serialVersionUID = -3387516993124229948L;

    public SysGuestServiceImpl() {
        super();
    }

    public SysGuestServiceImpl(String message) {
        super(message);
    }


    @Override
    public int insertSysGuest(SysGuest sysGuest)
    {

        String lou = sysGuest.getGuestDept();
        Long sushe = sysGuest.getGuestRoom();
        String number=sysGuest.getGuestPhoneNumber();
        //if(number==null){
         //   AjaxResult.error("手机号不能为空");
         //   System.out.println("wqweq");
       // }
        /*if(sushe==null){
            int exist = sysDeptMapper.findExist2(lou);
            if(exist > 0){
                System.out.println("exist = " + exist + "**********************************************");
                 return sysGuestMapper.insertSysGuest(sysGuest);
            }else
                return 0;
        }
        else {*/

        try {
                int p;
                p=1/number.length();

            try {
                int exist = sysDeptMapper.findExist(lou, sushe);
                if (exist > 0) {
                    System.out.println("exist = " + exist + "**********************************************");
                    return sysGuestMapper.insertSysGuest(sysGuest);

                } else
                    return 0;
            } catch (Exception e) {
                AjaxResult.error("访问去向楼栋和宿舍号不存在");
                return 0;
            }
        }catch (Exception e){
            AjaxResult.error("手机号不能为空");
            return 0;
        }

        }



    /**
     * 修改【请填写功能名称】
     * 
     * @param sysGuest 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysGuest(SysGuest sysGuest)
    {
        try{
            String lou = sysGuest.getGuestDept();
            Long sushe = sysGuest.getGuestRoom();
            int exist = sysDeptMapper.findExist(lou,sushe);
            if(exist > 0){
                System.out.println("exist = " + exist+"**********************************************");
                return sysGuestMapper.updateSysGuest(sysGuest);
            }
            else
                return 0;}
        catch( Exception e){
            return 0;
        }

    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param guestIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysGuestByGuestIds(Long[] guestIds)
    {
        return sysGuestMapper.deleteSysGuestByGuestIds(guestIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param guestId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSysGuestByGuestId(Long guestId)
    {
        return sysGuestMapper.deleteSysGuestByGuestId(guestId);
    }
}
