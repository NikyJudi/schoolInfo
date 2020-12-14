package com.learn.service;

import com.learn.entity.KcEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程
 * 
 
 * 
 */
public interface KcService {
    /**
    * 查询
	* @return
	*/
	KcEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<KcEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(KcEntity kc);

    /**
    * 修改
    * @return
    */
	void update(KcEntity kc);

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
