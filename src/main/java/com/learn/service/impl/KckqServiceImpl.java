package com.learn.service.impl;

import com.learn.dao.KcDao;
import com.learn.entity.KcEntity;
import com.learn.entity.SysUserEntity;
import com.learn.entity.YyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.learn.service.SysUserService;


import com.learn.dao.KckqDao;
import com.learn.entity.KckqEntity;
import com.learn.service.KckqService;
import com.learn.service.*;


@Service("kckqService")
public class KckqServiceImpl implements KckqService {
    @Autowired
    private KckqDao kckqDao;


    @Autowired
    private SysUserService sysUserService;


    @Autowired
    private KcService kcService;

    @Autowired
    private KcDao kcDao;


    @Override
    public List<Map<String, Object>> tjlist(Map<String, Object> map) {
        return kckqDao.tjlist(map);
    }

    @Override
    public int tjtotal(Map<String, Object> map) {
        return kckqDao.tjtotal(map);
    }

    @Override
    public KckqEntity queryObject(Long id) {
        KckqEntity entity = kckqDao.queryObject(id);


        if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
            entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));


        if (entity.getKc() != null && this.kcService.queryObject(entity.getKc()) != null)
            entity.setKcEntity(this.kcService.queryObject(entity.getKc()));

        if (this.sysUserService.queryObject(entity.getUser()) != null)
            entity.setUserEntity(this.sysUserService.queryObject(entity.getUser()));


        return entity;
    }

    @Override
    public List<KckqEntity> queryList(Map<String, Object> map) {
//        List<KckqEntity> list = kckqDao.queryList(map);
        List<KckqEntity> list = new ArrayList<>();
        String name = (String) map.get("name");
        if (name == null || name.equals("") || name.trim().equals("")) {
            list = kckqDao.queryList(map);
            for (KckqEntity entity : list) {
                if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
                    entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));
                if (entity.getKc() != null && this.kcService.queryObject(entity.getKc()) != null)
                    entity.setKcEntity(this.kcService.queryObject(entity.getKc()));
                if (this.sysUserService.queryObject(entity.getUser()) != null)
                    entity.setUserEntity(this.sysUserService.queryObject(entity.getUser()));
            }
        } else {
            name = name.trim();
            List<SysUserEntity> userList = sysUserService.queryByName(name);

            for (SysUserEntity user : userList) {
                Long userId = user.getUserId();
                List<KckqEntity> kckqEntities = kckqDao.queryListById(userId);
                for (KckqEntity entity : kckqEntities) {
                    entity.setSysUserEntity(user);
                    if (entity.getKc() != null && this.kcService.queryObject(entity.getKc()) != null)
                        entity.setKcEntity(this.kcService.queryObject(entity.getKc()));
                    if (this.sysUserService.queryObject(entity.getUser()) != null)
                        entity.setUserEntity(this.sysUserService.queryObject(entity.getUser()));
                }
                list.addAll(kckqEntities);

            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return kckqDao.queryTotal(map);
    }

    @Override
    public void save(KckqEntity kckq) {
        kckqDao.save(kckq);
    }

    @Override
    public void update(KckqEntity kckq) {
        kckqDao.update(kckq);
    }

    @Override
    public void delete(Long id) {
        kckqDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        kckqDao.deleteBatch(ids);
    }

}
