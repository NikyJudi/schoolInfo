package com.learn.service.impl;

import com.learn.entity.StEntity;
import com.learn.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.learn.dao.GykqDao;
import com.learn.entity.GykqEntity;
import com.learn.service.GykqService;
import com.learn.service.*;


@Service("gykqService")
public class GykqServiceImpl implements GykqService {
    @Autowired
    private GykqDao gykqDao;


    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<Map<String, Object>> tjlist(Map<String, Object> map) {
        return gykqDao.tjlist(map);
    }

    @Override
    public int tjtotal(Map<String, Object> map) {
        return gykqDao.tjtotal(map);
    }

    @Override
    public GykqEntity queryObject(Long id) {
        GykqEntity entity = gykqDao.queryObject(id);


        if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
            entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));


        return entity;
    }

    @Override
    public List<GykqEntity> queryList(Map<String, Object> map) {
        List<GykqEntity> list = new ArrayList<>();
        String name = (String) map.get("name");
        if (name == null || name.equals("") || name.trim().equals("")) {
            list = gykqDao.queryList(map);
            for (GykqEntity entity : list) {
                if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
                    entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));
            }
        } else {
            name = name.trim();
            List<SysUserEntity> userList = sysUserService.queryByName(name);
            for (SysUserEntity user: userList){
                Long userId = user.getUserId();
                List<GykqEntity> gykqEntities = gykqDao.queryListById(userId);
                for (GykqEntity entity : gykqEntities){
                    entity.setSysUserEntity(user);
                }
                list.addAll(gykqEntities);
            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return gykqDao.queryTotal(map);
    }

    @Override
    public void save(GykqEntity gykq) {
        gykqDao.save(gykq);
    }

    @Override
    public void update(GykqEntity gykq) {
        gykqDao.update(gykq);
    }

    @Override
    public void delete(Long id) {
        gykqDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        gykqDao.deleteBatch(ids);
    }

}
