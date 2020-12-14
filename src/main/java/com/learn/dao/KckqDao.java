package com.learn.dao;

import com.learn.entity.KckqEntity;

import java.util.List;
import java.util.Map;

/**
 * 课程考勤数据
 *

 *
 */
public interface KckqDao extends BaseDao<KckqEntity> {
    List<Map<String,Object>> tjlist(Map<String,Object> map);
    int tjtotal(Map<String,Object> map);
}
