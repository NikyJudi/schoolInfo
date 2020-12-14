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

import com.learn.entity.KcEntity;
import com.learn.service.KcService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 课程
 * 
 * 
 * 
 */
@RestController
@RequestMapping("kc")
public class KcController extends AbstractController {
	@Autowired
	private KcService kcService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){

												

		//查询列表数据
        Query query = new Query(params);

		List<KcEntity> kcList = kcService.queryList(query);
		int total = kcService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(kcList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	

	/**
	 * 列表
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		List<KcEntity> kcList = kcService.queryList(query);
		return R.ok().put("list", kcList );
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		KcEntity kc = kcService.queryObject(id);
		
		return R.ok().put("kc", kc);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody KcEntity kc){

												

        kcService.save(kc);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody KcEntity kc){
		kcService.update(kc);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		kcService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
