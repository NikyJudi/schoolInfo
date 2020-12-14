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

import com.learn.entity.ZyEntity;
import com.learn.service.ZyService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 
 * 专业管理
 * 
 * 
 */
@RestController
@RequestMapping("zy")
public class ZyController extends AbstractController {
	@Autowired
	private ZyService zyService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){

		//查询列表数据
        Query query = new Query(params);

		List<ZyEntity> zyList = zyService.queryList(query);
		int total = zyService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(zyList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<ZyEntity> zyList = zyService.queryList(query);
		return R.ok().put("list", zyList );
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		ZyEntity zy = zyService.queryObject(id);
		
		return R.ok().put("zy", zy);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody ZyEntity zy){

												

        zyService.save(zy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody ZyEntity zy){
		zyService.update(zy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		zyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
