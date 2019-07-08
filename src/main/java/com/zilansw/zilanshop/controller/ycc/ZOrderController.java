package com.zilansw.zilanshop.controller.ycc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.pojo.ZOrder;
import com.zilansw.zilanshop.service.ycc.ZOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("order")
public class ZOrderController
{

    @Autowired
    private ZOrderService service;

    /**
     * 获取订单列表
     * @return
     */
    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestParam(name = "pageindex",defaultValue = "1") Integer pageindex, @RequestParam(name = "pagesize",defaultValue = "20") Integer pagesize)
    {
        try
        {
            Page<ZOrder> page = service.getList(pageindex, pagesize);
            return MessageBack.DATA(200,"订单列表数据获取成功",page);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"订单列表数据获取失败");
        }
    }

    /**
     * 获取订单信息
     * @param orderid
     * @return
     */
    @RequestMapping("/get")
    public Map<String,Object> getOrder(Integer orderid)
    {
        try
        {
            ZOrder order = service.getOrder(orderid);
            return MessageBack.DATA(200,"订单数据获取成功",order);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"订单信息获取失败");
        }
    }


    /**
     * 修改订单信息
     * @param order
     * @return
     */
    @RequestMapping("/update")
    public Map<String,Object> updateOrder(ZOrder order)
    {
        try
        {
            service.updateOrder(order);
            return MessageBack.MSG(200,"订单更新成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"订单更新失败");
        }
    }


    /**
     * 创建订单
     * @param order
     * @return
     */
    @RequestMapping("/add")
    public Map<String,Object> addOrder(ZOrder order)
    {
        try
        {
            service.addOrder(order);
            return MessageBack.MSG(200,"订单创建成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"订单创建失败");
        }
    }
}
