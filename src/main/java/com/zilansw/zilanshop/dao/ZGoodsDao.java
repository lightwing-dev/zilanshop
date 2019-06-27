package com.zilansw.zilanshop.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZGoods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * @author : tjt
 * 创建时间 : 2019-06-27
 */
@Mapper
public interface ZGoodsDao extends BaseMapper<ZGoods> {

    /**
     * 查询商品库存
     * @param gname
     * @param pageIndex
     * @param limit
     * @return
     */
    List<ZGoods> selectPage(@Param("gname") String gname, @Param("pageIndex")Integer pageIndex, @Param("limit")Integer limit);


}