package com.learn.service.impl;

import com.learn.entity.SysUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.learn.dao.YyDao;
import com.learn.entity.YyEntity;
import com.learn.service.YyService;
import com.learn.service.*;


@Service("yyService")
public class YyServiceImpl implements YyService {
    @Autowired
    private YyDao yyDao;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<Map<String, Object>> tjlist(Map<String, Object> map) {
        return yyDao.tjlist(map);
    }

    @Override
    public int tjtotal() {
        return yyDao.total();
    }

    @Override
    public int total() {
        return yyDao.total();
    }

    @Override
    public YyEntity queryObject(Integer id) {
        YyEntity entity = yyDao.queryObject(id);


        if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
            entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));


        return entity;
    }

    @Override
    public List<YyEntity> queryList(Map<String, Object> map) {

//        List<YyEntity> list = yyDao.queryList(map);
        List<YyEntity> list = new ArrayList<>();
        String name = (String) map.get("name");
        if (name == null || name.equals("") || name.trim().equals("")) {
            list = yyDao.queryList(map);
            for (YyEntity entity : list) {
                if (entity.getSysUser() != null && this.sysUserService.queryObject(entity.getSysUser()) != null)
                    entity.setSysUserEntity(this.sysUserService.queryObject(entity.getSysUser()));
            }
        } else {
            name = name.trim();
            List<SysUserEntity> userList = sysUserService.queryByName(name);
            for (SysUserEntity user : userList) {
                Long userId = user.getUserId();
                List<YyEntity> yyEntityList = yyDao.queryListById(userId);
                for (YyEntity entity : yyEntityList) {
                    entity.setSysUserEntity(user);
                }
                list.addAll(yyEntityList);

            }
        }
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return yyDao.queryTotal(map);
    }

    @Override
    public void save(YyEntity yy) {
        yyDao.save(yy);
    }

    @Override
    public void update(YyEntity yy) {
        yyDao.update(yy);
    }

    @Override
    public void delete(Integer id) {
        yyDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        yyDao.deleteBatch(ids);
    }

}
