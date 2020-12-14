package com.learn.service.impl;

import com.learn.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.learn.dao.StDao;
import com.learn.entity.StEntity;
import com.learn.service.StService;
import com.learn.service.*;


@Service("stService")
public class StServiceImpl implements StService {
    @Autowired
    private StDao stDao;


    @Autowired
    private SysUserService sysUserService;


    @Override
    public List<Map<String, Object>> tjlist(Map<String, Object> map) {
        return stDao.tjlist(map);
    }

    @Override
    public int tjtotal() {
        return stDao.tjtotal();
    }

    @Override
    public int total() {
        return stDao.total();
    }

    @Override
    public StEntity queryObject(Long id) {
        StEntity entity = stDao.queryObject(id);


        if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
            entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));


        return entity;
    }

    @Override
    public List<StEntity> queryList(Map<String, Object> map) {
//        List<StEntity> list = stDao.queryList(map);
        List<StEntity> list = new ArrayList<>();
        String name = (String) map.get("name");
        if (name == null || name.equals("") || name.trim().equals("")) {
            list = stDao.queryList(map);
            for (StEntity entity : list) {
                if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
                    entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));

            }
        } else {
            name = name.trim();
            List<SysUserEntity> userList = sysUserService.queryByName(name);
            for (SysUserEntity user: userList){
                Long userId = user.getUserId();
                List<StEntity> stEntities = stDao.queryListById(userId);
                for (StEntity entity : stEntities){
                    entity.setSysUserEntity(user);
                }
                list.addAll(stEntities);
            }

            }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return stDao.queryTotal(map);
    }

    @Override
    public void save(StEntity st) {
        stDao.save(st);
    }

    @Override
    public void update(StEntity st) {
        stDao.update(st);
    }

    @Override
    public void delete(Long id) {
        stDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        stDao.deleteBatch(ids);
    }

}
