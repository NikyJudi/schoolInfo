package com.learn.service.impl;

import com.learn.dao.SysUserDao;
import com.learn.service.*;
import com.learn.utils.Constant;
import com.learn.utils.RRException;
import com.learn.entity.SysUserEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 系统用户
 * 
 *
 *
 * @date 18#9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private ZyService zyService;
	@Autowired
	private BjService bjService;


	@Override
	public List<String> queryAllPerms(Long userId) {
		return sysUserDao.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return sysUserDao.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}
	
	@Override
	public SysUserEntity queryObject(Long userId) {
		SysUserEntity entity = sysUserDao.queryObject(userId);
		if ( entity.getZy() != null &&  this.zyService.queryObject(entity.getZy().intValue()) != null)
			entity.setZyEntity(this.zyService.queryObject(entity.getZy().intValue()));
		if ( entity.getBj() != null &&  this.bjService.queryObject(entity.getBj()) != null)
			entity.setBjEntity(this.bjService.queryObject(entity.getBj()));
		return entity;
	}

	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		List<SysUserEntity> list = sysUserDao.queryList(map);
		for(SysUserEntity entity : list){
			if ( entity.getZy() != null &&  this.zyService.queryObject(entity.getZy().intValue()) != null)
				entity.setZyEntity(this.zyService.queryObject(entity.getZy().intValue()));
			if ( entity.getBj() != null &&  this.bjService.queryObject(entity.getBj()) != null)
				entity.setBjEntity(this.bjService.queryObject(entity.getBj()));
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		sysUserDao.save(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		}
		sysUserDao.update(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] userId) {
		sysUserDao.deleteBatch(userId);
	}

	@Override
	public int updatePassword(Long userId, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());
		
		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}


	public List<SysUserEntity> queryByName(String name){
		return sysUserDao.queryByName(name);
	}
}
