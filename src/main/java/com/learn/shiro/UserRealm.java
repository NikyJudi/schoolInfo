package com.learn.shiro;

import com.learn.entity.SysMenuEntity;
import com.learn.service.SysMenuService;
import com.learn.service.SysUserService;
import com.learn.utils.Constant;
import com.learn.entity.SysUserEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证用户登录的类是UserRealm
 * 认证
 * 
 *
 *
 * 10#11:55:49
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;
    
    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		Long userId = user.getUserId();
		
		List<String> permsList = null;
		
		//系统管理员，拥有最高权限
		if(userId == Constant.SUPER_ADMIN){
			List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<>(menuList.size());
			for(SysMenuEntity menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = sysUserService.queryAllPerms(userId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		//拿到账号密码
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        
        //查询用户信息，根据用户名查询用户
        SysUserEntity user = sysUserService.queryByUserName(username);
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        //组成用户对象返回
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
	}

}
