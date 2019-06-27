package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZAddress;
import com.zilansw.zilanshop.service.tjt.ZAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("zaddress")
public class ZAddressController {

    @Autowired
    private ZAddressService zAddressService;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public PageBean selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit,String name) {
        Page<ZAddress> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZAddress> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        IPage<ZAddress> iPage = zAddressService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(),(int) iPage.getSize(),Integer.parseInt(String.valueOf(iPage.getTotal())),Collections.singletonList(iPage.getRecords()));
        return pageBean;
    }

    /**
     * 新增收货地址
     * @param zAddress
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZAddress zAddress){
        zAddressService.insert(zAddress);
        return MessageBack.MSG(200,"新增成功");
    }

    /**
     * 新增收货地址
     * @param zAddress
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZAddress zAddress){
        zAddressService.update(zAddress);
        return MessageBack.MSG(200,"修改成功");
    }

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer aid){
        zAddressService.delete(aid);
        return MessageBack.MSG(200,"删除成功");
    }
}
