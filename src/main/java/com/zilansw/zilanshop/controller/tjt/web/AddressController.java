package com.zilansw.zilanshop.controller.tjt.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZAddress;
import com.zilansw.zilanshop.service.tjt.ZAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-28
 */
@Controller
@RequestMapping("address_web")
public class AddressController {

    @Autowired
    private ZAddressService zAddressService;

    /**
     * 分页查询当前用户的收货地址
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, Integer uid) {
        Page<ZAddress> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZAddress> queryWrapper = new QueryWrapper<>();
        if (uid==null){
           return MessageBack.MSG(401,"查询是遇到了一个预期外的错误");
        }
        queryWrapper.eq("uid",uid);
        IPage<ZAddress> iPage = zAddressService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(),(int) iPage.getSize(),Integer.parseInt(String.valueOf(iPage.getTotal())),Collections.singletonList(iPage.getRecords()));
        return MessageBack.DATA(200,"",pageBean);
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
     * 修改收货地址
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
