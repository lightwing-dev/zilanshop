package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.pojo.ZAdmin;
import com.zilansw.zilanshop.service.tjt.ZAdminService;
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
@RequestMapping("zAdmin")
public class ZAdminController {

    @Autowired
    private ZAdminService ZAdminService;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public PageBean selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit,String name) {
        Page<ZAdmin> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZAdmin> queryWrapper = new QueryWrapper<>();
        if (name!="" && name!=null){
            queryWrapper.eq("name",name);
        }
        IPage<ZAdmin> iPage = ZAdminService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(),(int) iPage.getSize(),Integer.parseInt(String.valueOf(iPage.getTotal())),Collections.singletonList(iPage.getRecords()));
        return pageBean;
    }

    /**
     * 新增管理员
     * @param ZAdmin
     * @return
     */
    @RequestMapping("insert")
    @ResponseBody
    public Map<String, Object> insert(ZAdmin ZAdmin){
        ZAdminService.insert(ZAdmin);
        return MessageBack.MSG(200,"新增成功");
    }

    /**
     * 新增管理员
     * @param ZAdmin
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZAdmin ZAdmin){
        ZAdminService.update(ZAdmin);
        return MessageBack.MSG(200,"修改成功");
    }

    /**
     * 删除管理员
     * @param aid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer aid){
        ZAdminService.delete(aid);
        return MessageBack.MSG(200,"删除成功");
    }
}
