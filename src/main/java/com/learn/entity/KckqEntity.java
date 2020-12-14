package com.learn.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 课程考勤数据
 */
public class KckqEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;

    //考勤学生
    private Long sysUser;

    private SysUserEntity sysUserEntity;


    //课程名称
    private Long kc;

    private KcEntity kcEntity;

    public KcEntity getKcEntity() {
        return kcEntity;
    }

    public void setKcEntity(KcEntity kcEntity) {
        this.kcEntity = kcEntity;
    }

    //教师
    private Long user;
    private SysUserEntity userEntity;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public SysUserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(SysUserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public SysUserEntity getSysUserEntity() {
        return sysUserEntity;
    }

    public void setSysUserEntity(SysUserEntity sysUserEntity) {
        this.sysUserEntity = sysUserEntity;
    }


    //上课地点
    private String skdd;

    //上课时间
    private Date time;

    //考勤结果
    private String kqjg;


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
     * 设置：考勤学生
     */
    public void setSysUser(Long sysUser) {
        this.sysUser = sysUser;
    }

    /**
     * 获取：考勤学生
     */
    public Long getSysUser() {
        return sysUser;
    }

    /**
     * 设置：课程名称
     */
    public void setKc(Long kc) {
        this.kc = kc;
    }

    /**
     * 获取：课程名称
     */
    public Long getKc() {
        return kc;
    }

    /**
     * 设置：教师
     */
    public void setUser(Long user) {
        this.user = user;
    }

    /**
     * 获取：教师
     */
    public Long getUser() {
        return user;
    }

    /**
     * 设置：上课地点
     */
    public void setSkdd(String skdd) {
        this.skdd = skdd;
    }

    /**
     * 获取：上课地点
     */
    public String getSkdd() {
        return skdd;
    }

    /**
     * 设置：上课时间
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 获取：上课时间
     */
    public Date getTime() {
        return time;
    }

    /**
     * 设置：考勤结果
     */
    public void setKqjg(String kqjg) {
        this.kqjg = kqjg;
    }

    /**
     * 获取：考勤结果
     */
    public String getKqjg() {
        return kqjg;
    }
}
