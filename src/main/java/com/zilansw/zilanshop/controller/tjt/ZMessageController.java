package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.commons.UPLOAD;
import com.zilansw.zilanshop.pojo.ZMessage;
import com.zilansw.zilanshop.service.tjt.ZMessageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-07-01
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("admin/zmessage")
public class ZMessageController {

    @Autowired
    private ZMessageService zMessageService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String name) {
        Map<String, Object> map = new HashMap<>();
        Page<ZMessage> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZMessage> queryWrapper = new QueryWrapper<>();
        if (name != "" && name != null) {
            queryWrapper.eq("name", name);
        }
        IPage<ZMessage> iPage = zMessageService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(), (int) iPage.getSize(), Integer.parseInt(String.valueOf(iPage.getTotal())));
        map.put("pageBean",pageBean);
        map.put("data",iPage.getRecords());
        return map;
//        return MessageBack.DATA(200,"",pageBean);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("selectById")
    @ResponseBody
    public Map<String, Object> selectById(long bid) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<ZMessage> queryWrapper = new QueryWrapper<>();
        ZMessage iPage = zMessageService.selectById(bid);
        map.put("data",iPage);
        return map;
//        return MessageBack.DATA(200,"",pageBean);
    }
    /**
     * 新增消息公告
     *
     * @param zMessage
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZMessage zMessage) {
        zMessage.setCreateTime(new Date());
        zMessageService.insert(zMessage);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改消息公告
     *
     * @param zMessage
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZMessage zMessage) {
        zMessageService.update(zMessage);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除消息公告
     *
     * @param mid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer mid) {
        zMessageService.delete(mid);
        return MessageBack.MSG(200, "删除成功");
    }
}
