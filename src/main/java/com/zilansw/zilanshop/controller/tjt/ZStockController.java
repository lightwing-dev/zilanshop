package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZStock;
import com.zilansw.zilanshop.service.tjt.ZGoodsService;
import com.zilansw.zilanshop.service.tjt.ZStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("admin/zStock")
public class ZStockController {

    @Autowired
    private ZStockService zStockService;
    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String,Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gname) {
        Map<String,Object>map = new HashMap<>();
        List<ZStock> iPage = zStockService.selectAll(gname,((pageIndex-1)*limit), limit);
        PageBean pageBean = new PageBean(pageIndex, limit, zStockService.selectCount(gname));
        map.put("data",iPage);
        map.put("pageBean",pageBean);
        return map;
    }


    /**
     * 根据编号查询
     *
     * @return
     */
    @RequestMapping("selectById")
    @ResponseBody
    public Map<String,Object> selectById(Integer sid) {
        Map<String,Object>map = new HashMap<>();
        ZStock iPage = zStockService.selectById(sid);
        map.put("data",iPage);
        return map;
    }

    /**
     * 新增库存
     *
     * @param zStock
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZStock zStock) {
        ZStock result = zStockService.selectByGid(zStock.getGid());
        if (result!=null){
            zStock.setStocknum(zStock.getStocknum()+result.getStocknum());
            zStock.setSid(result.getSid());
            zStockService.update(zStock);
            return MessageBack.MSG(200, "新增成功");
        }
        zStockService.insert(zStock);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改库存
     *
     * @param zStock
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZStock zStock) {
        zStockService.update(zStock);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除库存
     *
     * @param sid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer sid) {
        zStockService.delete(sid);
        return MessageBack.MSG(200, "删除成功");
    }
}
