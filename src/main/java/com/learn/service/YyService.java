package com.learn.service;

import com.learn.entity.YyEntity;

import java.util.List;
import java.util.Map;

/**
 * 医院就医数据记录
 *

 *
 */
public interface YyService {
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal();
	int total();
    /**
    * 查询
	* @return
	*/
	YyEntity queryObject(Integer id);

    /**
    * 查询列表
    * @return
    */
	List<YyEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(YyEntity yy);

    /**
    * 修改
    * @return
    */
	void update(YyEntity yy);

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
