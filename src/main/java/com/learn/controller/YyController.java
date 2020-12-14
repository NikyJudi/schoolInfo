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

import com.learn.entity.YyEntity;
import com.learn.service.YyService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 医院就医数据记录
 */
@RestController
@RequestMapping("yy")
public class YyController extends AbstractController {
    @Autowired
    private YyService yyService;

    @RequestMapping("/total")
    public R total() {
        int total = yyService.total();
        return R.ok().put("total", total);
    }

    /**
     * 列表
     */
    @RequestMapping("/tjlist")
    public R tjlist(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> tjlist = yyService.tjlist(query);
        int total = yyService.tjtotal();
        PageUtils pageUtil = new PageUtils(tjlist, total, query.getLimit(), query.getPage());
        return R.ok().put("page", pageUtil);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {


        //查询列表数据
        Query query = new Query(params);
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        if (null != userEntity.getType() && "1".equals(userEntity.getType())){
            query.put("sysUser",userEntity.getUserId());
        }
        List<YyEntity> yyList = yyService.queryList(query);
//        int total = yyService.queryTotal(query);
        int total = yyList.size();

        PageUtils pageUtil = new PageUtils(yyList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        if (null != userEntity.getType() && "1".equals(userEntity.getType())){
            query.put("sysUser",userEntity.getUserId());
        }
        List<YyEntity> yyList = yyService.queryList(query);
        return R.ok().put("list", yyList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        YyEntity yy = yyService.queryObject(id);

        return R.ok().put("yy", yy);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YyEntity yy) {


        yyService.save(yy);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody YyEntity yy) {
        yyService.update(yy);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        yyService.deleteBatch(ids);

        return R.ok();
    }

}
