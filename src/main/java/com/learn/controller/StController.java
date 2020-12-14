package com.learn.controller;

import java.util.List;
import java.util.Map;

import com.learn.entity.SysUserEntity;
import com.learn.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.entity.StEntity;
import com.learn.service.StService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 食堂刷卡记录
 *
 *
 *
 */
@RestController
@RequestMapping("st")
public class StController extends AbstractController {
	@Autowired
	private StService stService;

	@RequestMapping("/total")
	public R total() {
		int total = stService.total();
		return R.ok().put("total", total);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/tjlist")
	public R tjlist(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<Map<String, Object>> tjlist = stService.tjlist(query);
		int total = stService.tjtotal();
		PageUtils pageUtil = new PageUtils(tjlist, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){



		//查询列表数据
        Query query = new Query(params);
		SysUserEntity userEntity = ShiroUtils.getUserEntity();
		if (null != userEntity.getType() && "1".equals(userEntity.getType())){
			query.put("sysUser",userEntity.getUserId());
		}
		List<StEntity> stList = stService.queryList(query);
//		int total = stService.queryTotal(query);
		int total = stList.size();
		PageUtils pageUtil = new PageUtils(stList, total, query.getLimit(), query.getPage());

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 列表
	 */
	@RequestMapping("/list2")
	public R list2(@RequestParam Map<String, Object> params){
        Query query = new Query(params);
		SysUserEntity userEntity = ShiroUtils.getUserEntity();
		if (null != userEntity.getType() && "1".equals(userEntity.getType())){
			query.put("sysUser",userEntity.getUserId());
		}
		List<StEntity> stList = stService.queryList(query);
		return R.ok().put("list", stList );
	}


	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		StEntity st = stService.queryObject(id);

		return R.ok().put("st", st);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody StEntity st){



        stService.save(st);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody StEntity st){
		stService.update(st);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		stService.deleteBatch(ids);

		return R.ok();
	}

}
