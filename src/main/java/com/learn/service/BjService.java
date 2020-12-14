package com.learn.service;

import com.learn.entity.BjEntity;

import java.util.List;
import java.util.Map;

/**
 * 班级
 * 
 BjService是一个接口类，类中的接口与BjDao.xml映射配置文件相对应
 * 
 */
public interface BjService {
    /**
    * 按照bj的id查询数据
	* @return
	*/
	BjEntity queryObject(Long id);

    /**
    * 查询列表，查询所有的bj表中的数据
    * @return
    */
	List<BjEntity> queryList(Map<String, Object> map);

    /**
    * 查询班级数量（表项的总数）
    * @return
    */
	int queryTotal(Map<String, Object> map);

    /**
    * 保存，将一个BjEntity bj对象插入班级表中
    * @return
    */
	void save(BjEntity bj);

    /**
    * 修改，将某个id的name改成新的name
    * @return
    */
	void update(BjEntity bj);

    /**
    * 删除，删除班级表中id为某个值的那一行
    * @return
    */
	void delete(Long id);

    /**
    * 批量删除,删除ids数组中的id
    * @return
    */
	void deleteBatch(Long[] ids);
}
