package com.learn.service;

import com.learn.entity.TsgEntity;

import java.util.List;
import java.util.Map;

/**
 * 图书馆刷卡记录
 *

 *
 */
public interface TsgService {
	int total();
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal();
    /**
    * 查询
	* @return
	*/
	TsgEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<TsgEntity> queryList(Map<String, Object> map, String name);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(TsgEntity tsg);

    /**
    * 修改
    * @return
    */
	void update(TsgEntity tsg);

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
