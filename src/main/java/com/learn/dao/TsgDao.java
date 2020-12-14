package com.learn.dao;

import com.learn.entity.TsgEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 图书馆刷卡记录
 *

 *
 */
public interface TsgDao extends BaseDao<TsgEntity> {
	List<Map<String,Object>> tjlist(Map<String,Object> map);
	int tjtotal();
	int total();

}
