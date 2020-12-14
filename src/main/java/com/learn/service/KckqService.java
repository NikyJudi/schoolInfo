package com.learn.service;

import com.learn.entity.KckqEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程考勤数据
 *

 *
 */
public interface KckqService {
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal(Map<String,Object> map);
    /**
    * 查询
	* @return
	*/
	KckqEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<KckqEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(KckqEntity kckq);

    /**
    * 修改
    * @return
    */
	void update(KckqEntity kckq);

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
