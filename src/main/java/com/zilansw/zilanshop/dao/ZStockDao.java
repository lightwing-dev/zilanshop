package com.zilansw.zilanshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZStock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : tjt
 * 创建时间 : 2019-06-26
 */
@Mapper
public interface ZStockDao extends BaseMapper<ZStock> {

    /**
     * 查询商品库存
     * @param gname
     * @param pageIndex
     * @param limit
     * @return
     */
    List<ZStock> selectPage(@Param("gname") String gname, @Param("pageIndex")Integer pageIndex, @Param("limit")Integer limit);

    /**
     * 根据商品编号查询
     * @param gid
     * @return
     */
    ZStock selectByGid(@Param("gid") Long gid);
}
