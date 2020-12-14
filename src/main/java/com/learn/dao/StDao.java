package com.learn.dao;

import com.learn.entity.StEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 食堂刷卡记录
 *

 *
 */
public interface StDao extends BaseDao<StEntity> {
    List<Map<String,Object>> tjlist(Map<String,Object> map);
    int tjtotal();
    int total();

}
