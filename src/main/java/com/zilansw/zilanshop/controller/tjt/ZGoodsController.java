package com.zilansw.zilanshop.controller.tjt;

import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZGoods;
import com.zilansw.zilanshop.service.tjt.ZGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("zGoods")
public class ZGoodsController {

    @Autowired
    private ZGoodsService zGoodsService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public PageBean selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gname) {
        List<ZGoods> iPage = zGoodsService.selectAll(gname,((pageIndex-1)*limit), limit);
        PageBean pageBean = new PageBean(pageIndex, limit, zGoodsService.selectCount(gname), iPage);
        return pageBean;
    }

    /**
     * 新增商品
     *
     * @param zGoods
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZGoods zGoods) {
        zGoodsService.insert(zGoods);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 新增商品
     *
     * @param zGoods
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZGoods zGoods) {
        zGoodsService.update(zGoods);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除商品
     *
     * @param gid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer gid) {
        zGoodsService.delete(gid);
        return MessageBack.MSG(200, "删除成功");
    }
}
