package com.learn.service.impl;

import com.learn.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


import com.learn.dao.TsgDao;
import com.learn.entity.TsgEntity;
import com.learn.service.TsgService;
import com.learn.service.*;


@Service("tsgService")
public class TsgServiceImpl implements TsgService {
    @Autowired
    private TsgDao tsgDao;


    @Autowired
    private SysUserService sysUserService;


    @Override
    public int total() {
        return tsgDao.total();
    }

    @Override
    public List<Map<String, Object>> tjlist(Map<String, Object> map) {
        return tsgDao.tjlist(map);
    }

    @Override
    public int tjtotal() {
        return tsgDao.queryTotal();
    }

    @Override
    public TsgEntity queryObject(Long id) {
        TsgEntity entity = tsgDao.queryObject(id);


        if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
            entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));


        return entity;
    }

    @Override
    public List<TsgEntity> queryList(Map<String, Object> map, String name) {
        List<TsgEntity> list = new ArrayList<>();
        if (name == null || name.equals("") || name.trim().equals("")){
            list = tsgDao.queryList(map);
            for (TsgEntity entity : list) {
                SysUserEntity sysUserEntity = this.sysUserService.queryObject(entity.getSysUser());
                if (entity.getSysUser() != null && sysUserEntity != null)
                    entity.setSysUserEntity(sysUserEntity);
            }
        } else {
            name = name.trim();
            List<SysUserEntity> userList = sysUserService.queryByName(name);
            for (SysUserEntity user: userList){
                Long userId = user.getUserId();
                List<TsgEntity> list1 = tsgDao.queryListById(userId);
                for (TsgEntity entity : list1){
                    entity.setSysUserEntity(user);
                }
                list.addAll(list1);

            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tsgDao.queryTotal(map);
    }

    @Override
    public void save(TsgEntity tsg) {
        tsgDao.save(tsg);
    }

    @Override
    public void update(TsgEntity tsg) {
        tsgDao.update(tsg);
    }

    @Override
    public void delete(Long id) {
        tsgDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        tsgDao.deleteBatch(ids);
    }

}
