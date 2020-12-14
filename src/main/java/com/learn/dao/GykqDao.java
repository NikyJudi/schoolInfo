package com.learn.dao;

import com.learn.entity.GykqEntity;

import java.util.List;
import java.util.Map;

/**
 * 公寓考勤数据
 *

 *
 */
public interface GykqDao extends BaseDao<GykqEntity> {
    List<Map<String,Object>> tjlist(Map<String,Object> map);
    int tjtotal(Map<String,Object> map);
}
