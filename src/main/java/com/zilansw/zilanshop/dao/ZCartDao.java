package com.zilansw.zilanshop.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZCart;
import com.zilansw.zilanshop.pojo.ZGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : tjt
 * 创建时间 : 2019-07-13
 */
@Mapper
public interface ZCartDao extends BaseMapper<ZCart> {

    /**
     * 查询商品
     * @param uid
     * @param pageIndex
     * @param limit
     * @return
     */
    List<ZCart> selectPage(@Param("uid") Integer uid, @Param("pageIndex") Integer pageIndex, @Param("limit") Integer limit);

    int add(@Param("zCart") ZCart zCart);
}