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

import com.learn.entity.KckqEntity;
import com.learn.service.KckqService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 课程考勤数据
 */
@RestController
@RequestMapping("kckq")
public class KckqController extends AbstractController {
    @Autowired
    private KckqService kckqService;


    /**
     * 列表
     */
    @RequestMapping("/tjlist")
    public R tjlist(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> tjlist = kckqService.tjlist(query);
        int total = kckqService.tjtotal(query);
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
        if (null != userEntity.getType() && "1".equals(userEntity.getType())) {
            query.put("sysUser", userEntity.getUserId());
        }


        List<KckqEntity> kckqList = kckqService.queryList(query);
//        int total = kckqService.queryTotal(query);
        int total = kckqList.size();

        PageUtils pageUtil = new PageUtils(kckqList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        SysUserEntity userEntity = ShiroUtils.getUserEntity();
        if (null != userEntity.getType() && "1".equals(userEntity.getType())) {
            query.put("sysUser", userEntity.getUserId());
        }
        List<KckqEntity> kckqList = kckqService.queryList(query);
        return R.ok().put("list", kckqList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        KckqEntity kckq = kckqService.queryObject(id);

        return R.ok().put("kckq", kckq);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KckqEntity kckq) {

        if (kckq.getUser() == null)
            kckq.setUser(super.getUserId());


        kckqService.save(kckq);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody KckqEntity kckq) {
        kckqService.update(kckq);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        kckqService.deleteBatch(ids);

        return R.ok();
    }

}
