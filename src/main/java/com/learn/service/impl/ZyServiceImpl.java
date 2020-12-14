package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

		

import com.learn.dao.ZyDao;
import com.learn.entity.ZyEntity;
import com.learn.service.ZyService;
import com.learn.service.*;



@Service("zyService")
public class ZyServiceImpl implements ZyService {
	@Autowired
	private ZyDao zyDao;

			

			

	

	
	@Override
	public ZyEntity queryObject(Integer id){
			ZyEntity entity = zyDao.queryObject(id);

							
		return entity;
	}
	
	@Override
	public List<ZyEntity> queryList(Map<String, Object> map){
        List<ZyEntity> list = zyDao.queryList(map);
        for(ZyEntity entity : list){
																			}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return zyDao.queryTotal(map);
	}
	
	@Override
	public void save(ZyEntity zy){
		zyDao.save(zy);
	}
	
	@Override
	public void update(ZyEntity zy){
		zyDao.update(zy);
	}
	
	@Override
	public void delete(Integer id){
		zyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		zyDao.deleteBatch(ids);
	}
	
}
