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

import com.learn.entity.GykqEntity;
import com.learn.service.GykqService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 公寓考勤数据
 */
@RestController
@RequestMapping("gykq")
public class GykqController extends AbstractController {
    @Autowired
    private GykqService gykqService;

    /**
     * 列表
     */
    @RequestMapping("/tjlist")
    public R tjlist(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<Map<String, Object>> tjlist = gykqService.tjlist(query);
        int total = gykqService.tjtotal(query);
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
        List<GykqEntity> gykqList = gykqService.queryList(query);
//        int total = gykqService.queryTotal(query);
        int total = gykqList.size();
        PageUtils pageUtil = new PageUtils(gykqList, total, query.getLimit(), query.getPage());

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
        List<GykqEntity> gykqList = gykqService.queryList(query);
        return R.ok().put("list", gykqList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        GykqEntity gykq = gykqService.queryObject(id);

        return R.ok().put("gykq", gykq);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GykqEntity gykq) {


        gykqService.save(gykq);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GykqEntity gykq) {
        gykqService.update(gykq);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        gykqService.deleteBatch(ids);

        return R.ok();
    }

}
