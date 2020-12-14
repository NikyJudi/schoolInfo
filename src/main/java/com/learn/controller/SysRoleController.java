package com.learn.controller;

import com.learn.entity.SysRoleEntity;
import com.learn.service.SysRoleMenuService;
import com.learn.service.SysRoleService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理
 * 
 *
 *
 * 8#2:18:33
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController extends AbstractController {
	//用户角色
	@Autowired
	private SysRoleService sysRoleService;
	//角色与菜单的对应关系
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 角色列表，spring可以自动把所有的参数以Map<String, Object>的结构封装到params变量中，包括POST和GET的参数
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//如果不是超级管理员，则只查询自己创建的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			params.put("createUserId", getUserId());
//		}
		
		//查询角色列表
		Query query = new Query(params);//将当前页码和条数也放进params中
		List<SysRoleEntity> list = sysRoleService.queryList(query);
		int total = sysRoleService.queryTotal(query);
		
		//将查询到的数据放入pageUtil中
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表，这里和BjController不太一样
	 */
	@RequestMapping("/select")
	public R select(){
		Map<String, Object> map = new HashMap<>();
		
		//如果不是超级管理员，则只查询自己所拥有的角色列表
//		if(getUserId() != Constant.SUPER_ADMIN){
//			map.put("createUserId", getUserId());
//		}
		List<SysRoleEntity> list = sysRoleService.queryList(map);
		
		return R.ok().put("list", list);
	}
	
	/**
	 * 角色信息，从角色数据库根据roleid查询出某个角色的数据，同时查询该角色对应的菜单，将菜单id也放在role实体中
	 * 这样role中的信息就是包含角色信息和菜单信息
	 * 返回包含这些信息的map
	 */
	@RequestMapping("/info/{roleId}")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity role = sysRoleService.queryObject(roleId);
		
		//查询角色对应的菜单，并将角色对应的菜单放入role角色实体中
		List<Long> menuIdList = sysRoleMenuService.queryMenuIdList(roleId);
		role.setMenuIdList(menuIdList);
		
		return R.ok().put("role", role);
	}
	
	/**
	 * 保存新角色
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysRoleEntity role){
		//获取用户id；就是这个role的创建用户id
		role.setCreateUserId(getUserId());
		sysRoleService.save(role);
		
		return R.ok();
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysRoleEntity role){

		role.setCreateUserId(getUserId());
		sysRoleService.update(role);
		
		return R.ok();
	}
	
	/**
	 * 删除角色
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
}
