package com.learn.service;

import com.learn.entity.StEntity;

import java.util.List;
import java.util.Map;

/**
 * 食堂刷卡记录
 *

 *
 */
public interface StService {
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal();
	int total();
    /**
    * 查询
	* @return
	*/
	StEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<StEntity> queryList(Map<String, Object> map);


    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(StEntity st);

    /**
    * 修改
    * @return
    */
	void update(StEntity st);

    /**
    * 删除
    * @return
    */
	void delete(Long id);

    /**
    * 批量删除
    * @return
    */
	void deleteBatch(Long[] ids);
}
