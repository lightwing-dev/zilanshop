package com.zilansw.zilanshop.service.ycc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZOrderDao;
import com.zilansw.zilanshop.pojo.ZOrder;
import com.zilansw.zilanshop.pojo.ZUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class ZOrderService
{
    @Autowired
    private ZOrderDao dao;

    /**
     * 分页获取订单列表
     * @param pageindex
     * @param pagesize
     * @return
     */
    public Page<ZOrder> getList(Integer pageindex,Integer pagesize)
    {
        return (Page<ZOrder>) dao.selectPage(
                new Page<ZOrder>(pageindex,pagesize),
                new QueryWrapper<ZOrder>()
        );
    }


    /**
     * 通过订单号获取订单以及订单详情信息
     * @param orderid
     * @return
     */
    public ZOrder getOrder(Integer orderid)
    {
        return dao.getOrder(orderid);
    }


    /**
     * 更新订单信息
     * @param order
     * @return
     */
    public Integer updateOrder(ZOrder order)
    {
        return dao.updateById(order);
    }

    /**
     * 创建订单
     * @param order
     * @return
     */
    public Integer addOrder(ZOrder order)
    {
        return dao.addOrder(order);
    }



}
