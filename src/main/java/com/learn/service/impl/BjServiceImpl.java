package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

		

import com.learn.dao.BjDao;
import com.learn.entity.BjEntity;
import com.learn.service.BjService;
import com.learn.service.*;



@Service("bjService")
public class BjServiceImpl implements BjService {
	@Autowired
	private BjDao bjDao;

			

			

	

	
	@Override
	public BjEntity queryObject(Long id){
			BjEntity entity = bjDao.queryObject(id);

							
		return entity;
	}
	
	@Override
	public List<BjEntity> queryList(Map<String, Object> map){
        List<BjEntity> list = bjDao.queryList(map);
        for(BjEntity entity : list){
																			}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bjDao.queryTotal(map);
	}
	
	@Override
	public void save(BjEntity bj){
		bjDao.save(bj);
	}
	
	@Override
	public void update(BjEntity bj){
		bjDao.update(bj);
	}
	
	@Override
	public void delete(Long id){
		bjDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		bjDao.deleteBatch(ids);
	}
	
}
