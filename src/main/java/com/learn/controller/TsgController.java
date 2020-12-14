package com.learn.controller;

import java.util.List;
import java.util.Map;

import com.learn.entity.SysUserEntity;
import com.learn.service.SysUserService;
import com.learn.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.learn.entity.TsgEntity;
import com.learn.service.TsgService;
import com.learn.utils.PageUtils;
import com.learn.utils.Query;
import com.learn.utils.R;


/**
 * 图书馆刷卡记录
 */
//@RestController注解是在处理器在处理完任务后返回json格式的数据，而不是跳转页面
//@RequestMapping注解用来映射请求，即指明处理器处理url为tsg的请求，

@RestController
@RequestMapping("tsg")
public class TsgController extends AbstractController {
    @Autowired
    private TsgService tsgService;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/total")
    public R total() {
        int total = tsgService.total();
        return R.ok().put("total", total);
    }

    /**
     * 列表
     */
    @RequestMapping("/tjlist")
    public R tjlist(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<Map<String, Object>> tjlist = tsgService.tjlist(query);
        int total = tsgService.tjtotal();
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

        List<TsgEntity> tsgList = tsgService.queryList(query, (String)params.get("name"));
//        int total = tsgService.queryTotal(query);
        int total = tsgList.size();
        PageUtils pageUtil = new PageUtils(tsgList, total, query.getLimit(), query.getPage());

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
        List<TsgEntity> tsgList = tsgService.queryList(query, (String) params.get("name"));
        return R.ok().put("list", tsgList);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        TsgEntity tsg = tsgService.queryObject(id);

        return R.ok().put("tsg", tsg);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TsgEntity tsg) {


        tsgService.save(tsg);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TsgEntity tsg) {
        tsgService.update(tsg);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        tsgService.deleteBatch(ids);

        return R.ok();
    }

}
