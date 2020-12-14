package com.learn.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.BjEntity;
import com.learn.service.BjService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 班级（id,name）两个属性
 * 注解是将Bean装配（依赖注入）到spring容器的一种方式。Spring容器用来管理bean
 * RestController相当于Controller与ResponseBody的结合，ResponseBody表示将该方法的返回结果写入HTTP response body中
 * RestController要求方法返回的是json格式数据，而不是跳转页面，返回数据给ajax
 * ajax（动态网页应用的网页开发技术，无需重新加载整个网页的情况下，能够更新部分网页的技术）
 * 
 * 
 * RequestMapping注解是用来映射请求的，即指明处理器可以处理哪些URL请求，该注解既可以用在类上，也可以用在方法上。
 * 将url中相对路径为bj的 HTTP请求方法映射到处理请求的BjController控制器中
 * 
 */
@RestController
@RequestMapping("bj")
public class BjController extends AbstractController {
	//Autowired注解消除setter和getter方法，默认按照Bean的类型进行装配
	//实例化BjService类型对象
	@Autowired
	private BjService bjService;
	
	/**
	 * 列表
	 * 
	 * RequestParam注解接收的参数是来自HTTP请求体或请求url的查询字符串
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){

		//Query本质是一个hashmap，query对象中存储有当前页码和条数，以及请求的数据
        Query query = new Query(params);
        //查询所有的bj表中的数据，BjController-->BjService--》BjDao--》BaseDao.java-->BjDao.xml
		List<BjEntity> bjList = bjService.queryList(query);
		//查询班级数量（表项的总数）
		int total = bjService.queryTotal(query);
		
		//PageUtils(列表数据，总记录数，每页条数，当前页码），页面要显示的所有数据放在pageUtil中
		PageUtils pageUtil = new PageUtils(bjList, total, query.getLimit(), query.getPage());
		
		//R.ok（）返回一个R对象，调用其put方法，将"page"和pageUtil对象存放在hashMap中
		return R.ok().put("page", pageUtil);
	}
	

	/**
	 * 列表
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<BjEntity> bjList = bjService.queryList(query);
		return R.ok().put("list", bjList );
	}


	/**
	 * 根据id查询数据
	 * PathVariable注解是接收请求路径/info/{id}中的id值
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		BjEntity bj = bjService.queryObject(id);
		
		return R.ok().put("bj", bj);
	}
	
	/**
	 * 保存，插入bj对象中的数据到bj数据库
	 * RequestBody用于读取request请求的body正文部分
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BjEntity bj){

												

        bjService.save(bj);
		
		return R.ok();
	}
	
	/**
	 * 修改，将某个id的班级name改成新的name
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BjEntity bj){
		bjService.update(bj);
		
		return R.ok();
	}
	
	/**
	 * 删除，批量删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		bjService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
