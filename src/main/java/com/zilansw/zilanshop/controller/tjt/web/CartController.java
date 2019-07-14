package com.zilansw.zilanshop.controller.tjt.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZCart;
import com.zilansw.zilanshop.pojo.ZOrder;
import com.zilansw.zilanshop.service.tjt.ZCartService;
import com.zilansw.zilanshop.service.ycc.ZOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cart_web")
public class CartController {

    @Autowired
    private ZCartService zCartService;

    /**
     * 根据用户编号查询商品订单
     *
     * @param pageIndex
     * @param limit
     * @return
     */
    @RequestMapping("selectByWeb")
    @ResponseBody
    public Map<String, Object> selectByWeb(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "8") Integer limit) {
        Integer uid = 1;//测试登录用户，本应获取sesion中已登录的用户编号
        Map<String, Object> map = new HashMap<>();
        List<ZCart> iPage = zCartService.getList(uid, ((pageIndex - 1) * limit), limit);
        QueryWrapper<ZCart> queryWrapper = new QueryWrapper<>();
        if (uid != null) {
            queryWrapper.eq("uid", uid);
        } else {
            return MessageBack.MSG(401, "在查询是遇到了一个预期外的错误");
        }
        PageBean pageBean = new PageBean(pageIndex, limit, zCartService.selectCount(queryWrapper));
        map.put("pageBean", pageBean);
        map.put("data", iPage);
        return map;
    }

    /**
     * 加入购物车
     *
     * @param gid
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> add(Integer gid) {
        Integer uid = 1;//测试登录用户，本应获取sesion中已登录的用户编号
        QueryWrapper<ZCart> queryWrapper = new QueryWrapper<>();
        if (uid != null) {
            queryWrapper.eq("uid", uid);
            queryWrapper.eq("gid", gid);
            ZCart result = zCartService.getByGid(queryWrapper);
            if (result != null) {
                return MessageBack.MSG(201, "该商品已存在购物车");
            }
        } else {
            return MessageBack.MSG(401, "请登录");
        }
        ZCart zCart = new ZCart();
        zCart.setGid(gid);
        zCart.setUid(uid);
        zCart.setCreateTime(new Date());
        zCartService.insert(zCart);
        return MessageBack.MSG(200, "成功加入购物车");
    }

    /**
     * 删除购物车商品
     *
     * @param gid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer gid) {
        Integer uid = 1;//测试登录用户，本应获取sesion中已登录的用户编号
        QueryWrapper<ZCart> queryWrapper = new QueryWrapper<>();
        if (uid != null) {
            queryWrapper.eq("uid", uid);
            queryWrapper.eq("gid", gid);
            zCartService.delete(queryWrapper);
            return MessageBack.MSG(200, "已成功移除出购物车");
        } else {
            return MessageBack.MSG(401, "请登录");
        }
    }

}
