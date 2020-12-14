package com.learn.service.impl;

import com.learn.service.SysMenuService;
import com.learn.service.SysRoleMenuService;
import com.learn.utils.Constant;
import com.learn.dao.SysMenuDao;
import com.learn.entity.SysMenuEntity;
import com.learn.service.SysUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	//根据父菜单，查询子菜单;在子菜单中查询父菜单为parentId的菜单，如果子菜单传入为空，就返回父菜单为parentId的数据
	//parentId是父菜单id，menuIdList是用户菜单id
	@Override
	public List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		//按照父id为parentId查询sys_menu表，结果按照升序排序
		List<SysMenuEntity> menuList = sysMenuDao.queryListParentId(parentId);
		//如果子菜单menuIdList为空，就返回父菜单的查询结果
		if(menuIdList == null){
			return menuList;
		}
		
		//传入的子菜单不为空
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		//遍历查询到的结果menuList
		for(SysMenuEntity menu : menuList){
			//如果用户菜单id中包含菜单id（menu_id）值，就将数据插入userMenuList中返回
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	//返回sys_menu表中类型不是按钮的数据
	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return sysMenuDao.queryNotButtonList();
	}

	//获取用户的菜单列表
	//如果是超级管理员，获取的是所有菜单；
	//如果不是超级管理员，则根据用户id（sys_user表中的user_id）获取该用户的菜单
	@Override
	public List<SysMenuEntity> getUserMenuList(Long userId) {
		//系统管理员，拥有最高权限
		if(userId ==  Constant.SUPER_ADMIN){
			//获取所有菜单列表
			return getAllMenuList(null);
		}
		
		//如果不是系统管理员，根据用户id查询用户的所有菜单id，返回该用户的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getAllMenuList(menuIdList);
	}
	
	//根据菜单id查询菜单
	@Override
	public SysMenuEntity queryObject(Long menuId) {
		return sysMenuDao.queryObject(menuId);
	}

	//查询菜单列表（所有菜单）
	@Override
	public List<SysMenuEntity> queryList(Map<String, Object> map) {
		return sysMenuDao.queryList(map);
	}
	
	//查询菜单的数量，也就是表sys_menu中的行数
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysMenuDao.queryTotal(map);
	}
	
	//保存表，是在sys_menu表中插入一项menu
	@Override
	public void save(SysMenuEntity menu) {
		sysMenuDao.save(menu);
	}

	//更新表，是在sys_menu表中更新与传入menu中menu_id相同的数据
	@Override
	public void update(SysMenuEntity menu) {
		sysMenuDao.update(menu);
	}

	//批量删除表项
	@Override
	@Transactional
	public void deleteBatch(Long[] menuIds) {
		sysMenuDao.deleteBatch(menuIds);
	}
	
	//获取用户id为userId的菜单列表
	@Override
	public List<SysMenuEntity> queryUserList(Long userId) {
		return sysMenuDao.queryUserList(userId);
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表，0L表示一级菜单，menuIdList为子菜单
		List<SysMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归，如果父菜单的类型是目录，就迭代获取目录下的子菜单
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<Long> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();
		
		for(SysMenuEntity entity : menuList){
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
