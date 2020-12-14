package com.learn.service;

import com.learn.entity.GykqEntity;

import java.util.List;
import java.util.Map;

/**
 * 公寓考勤数据
 *

 *
 */
public interface GykqService {
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal(Map<String,Object> map);
    /**
    * 查询
	* @return
	*/
	GykqEntity queryObject(Long id);

    /**
    * 查询列表
    * @return
    */
	List<GykqEntity> queryList(Map<String, Object> map);

    /**
    * 查询总数
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存
    * @return
    */
	void save(GykqEntity gykq);

    /**
    * 修改
    * @return
    */
	void update(GykqEntity gykq);

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
