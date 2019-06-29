package com.zilansw.zilanshop.controller.tjt.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZBanner;
import com.zilansw.zilanshop.service.tjt.ZBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("banner_web")
public class BannerController {

    @Autowired
    private ZBannerService zBannerService;

    /**
     * 分页查询轮播图信息
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String,Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String name) {
        Page<ZBanner> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZBanner> queryWrapper = new QueryWrapper<>();
        if (name!="" && name!=null){
            queryWrapper.eq("name",name);
        }
        IPage<ZBanner> iPage = zBannerService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(), (int) iPage.getSize(), Integer.parseInt(String.valueOf(iPage.getTotal())), Collections.singletonList(iPage.getRecords()));
        return MessageBack.DATA(200,"",pageBean);
    }

}
