package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZGoodstype;
import com.zilansw.zilanshop.service.tjt.ZGoodstypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("zGoodstype")
public class ZGoodstypeController {

    @Autowired
    private ZGoodstypeService zGoodstypeService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public PageBean selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gtypename) {
        Page<ZGoodstype> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZGoodstype> queryWrapper = new QueryWrapper<>();
        if (gtypename!="" && gtypename!=null){
            queryWrapper.eq("gtypename", gtypename);
        }
        IPage<ZGoodstype> iPage = zGoodstypeService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(), (int) iPage.getSize(), Integer.parseInt(String.valueOf(iPage.getTotal())), Collections.singletonList(iPage.getRecords()));
        return pageBean;
    }

    /**
     * 新增商品分类
     *
     * @param zGoodstype
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZGoodstype zGoodstype) {
        zGoodstypeService.insert(zGoodstype);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 新增商品分类
     *
     * @param zGoodstype
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZGoodstype zGoodstype) {
        zGoodstypeService.update(zGoodstype);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除商品分类
     *
     * @param gtypeid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer gtypeid) {
        zGoodstypeService.delete(gtypeid);
        return MessageBack.MSG(200, "删除成功");
    }
}
