package com.zilansw.zilanshop.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZEvaluation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author : tjt
 * 创建时间 : 2019-06-27
 */
@Mapper
public interface ZEvaluationDao extends BaseMapper<ZEvaluation> {
    /**
     * 查询评价
     * @param result
     * @param pageIndex
     * @param limit
     * @return
     */
    List<ZEvaluation> selectPage(@Param("result") Map<String,Object> result, @Param("pageIndex")Integer pageIndex, @Param("limit")Integer limit);


}