package com.zilansw.zilanshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : clarence
 * 创建时间 : 2019-06-26
 * 长沙轻翼网络科技有限公司
 */
@Mapper
public interface ZOrderDao extends BaseMapper<ZOrder> {
    /**
     * 添加订单信息
     * @param order
     * @return
     */
    Integer addOrder(@Param("order") ZOrder order);

    /**
     * 获取订单信息
     * @param orderid
     * @return
     */
    ZOrder getOrder(@Param("orderid") Integer orderid);
}
