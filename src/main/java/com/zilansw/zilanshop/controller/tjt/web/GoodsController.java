package com.zilansw.zilanshop.controller.tjt.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZGoods;
import com.zilansw.zilanshop.pojo.ZGoodsimage;
import com.zilansw.zilanshop.pojo.ZGoodstype;
import com.zilansw.zilanshop.service.tjt.ZGoodsService;
import com.zilansw.zilanshop.service.tjt.ZGoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @Autowired
    private ZGoodstypeService zGoodstypeService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getByName")
    @ResponseBody
    public Map<String, Object> selectByWeb(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "8") Integer limit, String gname, Integer gtypeid, String price, String salesVolume) {
        Map<String, Object> reulst = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<ZGoodstype> zGoodstypeQueryWrapper = new QueryWrapper<>();
        if (gname != null && gname != "") {
            map.put("gname", "%" + gname + "%");
        }
        if (gtypeid != null) {
            zGoodstypeQueryWrapper.eq("parentid", gtypeid);
            List<ZGoodstype> oneLevelList = zGoodstypeService.selectByPid(zGoodstypeQueryWrapper);
            if (oneLevelList.size() != 0) {
                QueryWrapper<ZGoodstype> oneQueryWrapper = new QueryWrapper<>();
                for (ZGoodstype one : oneLevelList) {
                    oneQueryWrapper.in("parentid", one.getGtypeid(), gtypeid);
                }
                List<ZGoodstype> twoLevelList = zGoodstypeService.selectByPid(oneQueryWrapper);
                if (twoLevelList.size() != 0) {
//                    List<Long> str = new ArrayList<>();
                    String str = "";
                    str += gtypeid + ",";
                    for (ZGoodstype two : twoLevelList) {
                        str = str + two.getGtypeid() + ",";
                    }
                    map.put("gtypeid", str.substring(0,(str.length()-1)));
                }else{
                    String str = "";
                    str += gtypeid + ",";
                    for (ZGoodstype one : oneLevelList) {
                        str = str + one.getGtypeid() + ",";
                    }
                }

            }else{
                map.put("gtypeid", gtypeid);
            }
        }
        if (price.equals("desc")) {
            reulst.put("price", "asc");
            map.put("price","desc");
        }
        if (price.equals("asc")) {
            reulst.put("price", "desc");
            map.put("price","asc");
//            queryWrapper.orderByAsc("price");
        }
        if (salesVolume.equals("desc")) {
            reulst.put("salesVolume", "asc");
            map.put("salesVolume","desc");
//            queryWrapper.orderByDesc("sales_volume");
        }
        if (salesVolume.equals("asc")) {
            reulst.put("salesVolume", "desc");
            map.put("salesVolume","asc");
//            queryWrapper.orderByAsc("sales_volume");
        }
        List<ZGoods> iPage = zGoodsService.selectByWeb(map, ((pageIndex - 1) * limit), limit);
        PageBean pageBean = new PageBean(pageIndex, limit, zGoodsService.selectCount(map));
        for (ZGoods str : iPage) {
            if (str.getZGoodsimage().size() != 0) {
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath());
                }
            }
        }
        reulst.put("pageBean", pageBean);
        reulst.put("data", iPage);
        return reulst;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getByTypeId")
    @ResponseBody
    public Map<String, Object> getByTypeId(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "8") Integer limit, Integer gtypeid) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> reulst = new HashMap<>();
        QueryWrapper<ZGoodstype> zGoodstypeQueryWrapper = new QueryWrapper<>();
        if (gtypeid != null) {
            zGoodstypeQueryWrapper.eq("parentid", gtypeid);
            List<ZGoodstype> oneLevelList = zGoodstypeService.selectByPid(zGoodstypeQueryWrapper);
            if (oneLevelList.size() != 0) {
                QueryWrapper<ZGoodstype> oneQueryWrapper = new QueryWrapper<>();
                for (ZGoodstype one : oneLevelList) {
                    oneQueryWrapper.in("parentid", one.getGtypeid(), gtypeid);
                }
                List<ZGoodstype> twoLevelList = zGoodstypeService.selectByPid(oneQueryWrapper);
                if (twoLevelList.size() != 0) {
//                    List<Long> str = new ArrayList<>();
                    String str = "";
                    str += gtypeid + ",";
                    for (ZGoodstype two : twoLevelList) {
                        str = str + two.getGtypeid() + ",";
                    }
                    map.put("gtypeid", str.substring(0,(str.length()-1)));
                }else{
                    String str = "";
                    str += gtypeid + ",";
                    for (ZGoodstype one : oneLevelList) {
                        str = str + one.getGtypeid() + ",";
                    }
                }

            }else{
                map.put("gtypeid", gtypeid);
            }
        }
        List<ZGoods> iPage = zGoodsService.selectByWeb(map, ((pageIndex - 1) * limit), limit);
        PageBean pageBean = new PageBean(pageIndex, limit, zGoodsService.selectCount(map));
        for (ZGoods str : iPage) {
            if (str.getZGoodsimage().size() != 0) {
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath());
                }
            }
        }
        reulst.put("pageBean", pageBean);
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
            if (str.getZGoodsimage().size() != 0) {
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath());
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
            if (str.getZGoodsimage().size() != 0) {
                for (ZGoodsimage str1 : str.getZGoodsimage()) {
                    str.setImg(str1.getImgpath());
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
        map.put("data", zGoods);
        return map;
    }
}
