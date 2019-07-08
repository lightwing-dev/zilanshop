package com.zilansw.zilanshop.controller.tjt.web;

import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZGoods;
import com.zilansw.zilanshop.pojo.ZGoodsimage;
import com.zilansw.zilanshop.service.tjt.ZGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-28
 */
@Controller
@RequestMapping("goods_web")
public class GoodsController {

    @Autowired
    private ZGoodsService zGoodsService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> selectByWeb(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gname, Integer gtypeid) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> reulst = new HashMap<>();
        if (gname != null && gname != "") {
            map.put("gname", gname);
        }
        if (gtypeid != null) {
            map.put("gtypeid", gtypeid);
        }
        List<ZGoods> iPage = zGoodsService.selectByWeb(map, ((pageIndex - 1) * limit), limit);

//        PageBean pageBean = new PageBean(pageIndex, limit, zGoodsService.selectCount(gname), iPage);
        for (ZGoods str : iPage) {
            if (str.getZGoodsimage().size()!=0){
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath().substring(0, str1.getImgpath().indexOf(",")));
                }
            }
        }
        reulst.put("data", iPage);
        return reulst;
    }


    /**
     * 最新商品
     *
     * @return
     */
    @RequestMapping("selectNewCreateTime")
    @ResponseBody
    public Map<String, Object> selectNewCreateTime(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer limit) {
        Map<String, Object> reulst = new HashMap<>();
        List<ZGoods> iPage = zGoodsService.getNewCreateTime(((pageIndex - 1) * limit), limit);
        for (ZGoods str : iPage) {
            if (str.getZGoodsimage().size()!=0){
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath().substring(0, str1.getImgpath().indexOf(",")));
                }
            }
        }
        reulst.put("data", iPage);
        return reulst;
    }

    /**
     * 最新商品
     *
     * @return
     */
    @RequestMapping("selectSalesVolume")
    @ResponseBody
    public Map<String, Object> selectSalesVolume(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer limit) {
        Map<String, Object> reulst = new HashMap<>();
        List<ZGoods> iPage = zGoodsService.selectSalesVolume(((pageIndex - 1) * limit), limit);
        for (ZGoods str : iPage) {
            if (str.getZGoodsimage().size()!=0){
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath().substring(0, str1.getImgpath().indexOf(",")));
                }
            }
        }
        reulst.put("data", iPage);
        return reulst;
    }


    /**
     * 根据编号查询商品详情
     *
     * @return
     */
    @RequestMapping("getById")
    @ResponseBody
    public Map<String, Object> getById(Integer gid) {
        Map<String, Object> map = new HashMap<>();
        if (gid == null) {
            return MessageBack.MSG(401, "在查询是遇到了一个预期外的错误");
        }
        List<ZGoods> zGoods = zGoodsService.getById(gid);
        return MessageBack.DATA(200, "", zGoods);
    }
}
