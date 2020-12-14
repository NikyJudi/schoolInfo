package com.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

		

import com.learn.dao.KcDao;
import com.learn.entity.KcEntity;
import com.learn.service.KcService;
import com.learn.service.*;



@Service("kcService")
public class KcServiceImpl implements KcService {
	@Autowired
	private KcDao kcDao;

			

			

	

	
	@Override
	public KcEntity queryObject(Long id){
			KcEntity entity = kcDao.queryObject(id);

							
		return entity;
	}
	
	@Override
	public List<KcEntity> queryList(Map<String, Object> map){
        List<KcEntity> list = kcDao.queryList(map);
        for(KcEntity entity : list){
																			}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return kcDao.queryTotal(map);
	}
	
	@Override
	public void save(KcEntity kc){
		kcDao.save(kc);
	}
	
	@Override
	public void update(KcEntity kc){
		kcDao.update(kc);
	}
	
	@Override
	public void delete(Long id){
		kcDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		kcDao.deleteBatch(ids);
	}
	
}
