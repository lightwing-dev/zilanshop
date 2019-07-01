package com.zilansw.zilanshop.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : tjt
 * 创建时间 : 2019-06-27
 */
@Mapper
public interface ZGoodsDao extends BaseMapper<ZGoods> {

    /**
     * 查询商品
     * @param gname
     * @param pageIndex
     * @param limit
     * @return
     */
    List<ZGoods> selectPage(@Param("gname") String gname, @Param("pageIndex")Integer pageIndex, @Param("limit")Integer limit);

    List<ZGoods> selectByWeb(@Param("map")Map<String,Object> map, @Param("pageIndex")Integer pageIndex, @Param("limit")Integer limit);

    List<ZGoods> getById(@Param("gid") Integer gid);

}