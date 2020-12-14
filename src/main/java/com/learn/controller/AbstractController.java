package com.learn.controller;

import com.learn.utils.ShiroUtils;
import com.learn.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller公共组件
 * 
 *Shiro 是一个强大而灵活的开源安全框架，它干净利落地处理身份认证，授权，企业会话管理和加密。
 *
 *Logger 负责处理日志记录的大部分操作
 */
public abstract class AbstractController {
	//使用指定类初始化日志对象 在日志输出的时候，可以打印出日志信息所在的类
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}
    //获取用户id
	protected Long getUserId() {
		return getUser().getUserId();
	}
}
