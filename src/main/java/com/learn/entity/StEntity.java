package com.learn.entity;

import java.io.Serializable;
import java.util.Date;

import com.learn.service.*;


/**
 * 食堂刷卡记录
 */
public class StEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;

    //学生
    private Long sysUser;

    private SysUserEntity sysUserEntity;

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }

    //消费金额
    private Double xfje;

    //消费时间
    private Date time;

    // 消费窗口
    private Integer xfck;

    public Integer getXfck() {
        return xfck;
    }

    public void setXfck(Integer xfck) {
        this.xfck = xfck;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：学生
     */
    public void setSysUser(Long sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 获取：学生
     */
    public Long getSysUser() {
        return sysUser;
    }

    /**
     * 设置：消费金额
     */
    public void setXfje(Double xfje) {
        this.xfje = xfje;
    }

    /**
     * 获取：消费金额
     */
    public Double getXfje() {
        return xfje;
    }

    /**
     * 设置：消费时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取：消费时间
     */
    public Date getTime() {
        return time;
    }
}
