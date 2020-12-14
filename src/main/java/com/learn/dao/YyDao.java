package com.learn.dao;

import com.learn.entity.YyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 医院就医数据记录
 *

 *
 */
public interface YyDao extends BaseDao<YyEntity> {
    List<Map<String,Object>> tjlist(Map<String,Object> map);
    int tjtotal();
    int total();

}
