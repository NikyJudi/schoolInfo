package com.learn.service;

import com.learn.entity.ZyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 
 * 
 */
public interface ZyService {
    /**
    * 查询
	* @return
	*/
	ZyEntity queryObject(Integer id);

    /**
    * 查询列表
    * @return
    */
	List<ZyEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(ZyEntity zy);

    /**
    * 修改
    * @return
    */
	void update(ZyEntity zy);

    /**
    * 删除
    * @return
    */
	void delete(Integer id);

    /**
    * 批量删除
    * @return
    */
	void deleteBatch(Integer[] ids);
}
