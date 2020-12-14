package com.learn.service.impl;

import com.learn.entity.SysRoleEntity;
import com.learn.service.SysRoleMenuService;
import com.learn.service.SysRoleService;
import com.learn.service.SysUserRoleService;
import com.learn.service.SysUserService;
import com.learn.utils.Constant;
import com.learn.utils.RRException;
import com.learn.dao.SysRoleDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * 角色
 * 
 *
 *
 * @date 18#9:45:12
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysUserService sysUserService;

	//根据角色id查询角色表信息
	@Override
	public SysRoleEntity queryObject(Long roleId) {
		return sysRoleDao.queryObject(roleId);
	}
    //根据role_name和create_user_id查询角色表，结果按照角色id升序排序
	@Override
	public List<SysRoleEntity> queryList(Map<String, Object> map) {
		return sysRoleDao.queryList(map);
	}

	//如果map中有数据，根据role_name和create_user_id查询角色表,返回数量
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysRoleDao.queryTotal(map);
	}

	//保存新角色；1.获取当前时间就是创建时间；2.将role插入到角色表中；
	//3.检查用户的菜单判断用户是否越权，越权就抛出异常，并显示提示信息;4.保存该角色和菜单的关系在角色菜单表中
	@Override
	@Transactional
	public void save(SysRoleEntity role) {
		role.setCreateTime(new Date());
		sysRoleDao.save(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//保存角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	//1.根据传入role的roleId更新角色表中的roleName和remark
	//2.检查该角色是否越权，越权就抛出异常
	//3.在角色与菜单表中插入该角色id以及对应的菜单id
	@Override
	@Transactional
	public void update(SysRoleEntity role) {
		sysRoleDao.update(role);
		
		//检查权限是否越权
		checkPrems(role);
		
		//更新角色与菜单关系
		sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] roleIds) {
		sysRoleDao.deleteBatch(roleIds);
	}
	
	//查询用户创建的角色ID列表
	@Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return sysRoleDao.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId()  == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表，通过关联用户和角色表；以及角色和菜单表，找到该角色创建者用户对应的菜单
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权，判断创建者的菜单中是否包含该角色的菜单，如果不包含就是超出了权限
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
