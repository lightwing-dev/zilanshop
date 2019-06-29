package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.commons.UPLOAD;
import com.zilansw.zilanshop.pojo.ZBanner;
import com.zilansw.zilanshop.service.tjt.ZBannerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("admin/zbanner")
public class ZBannerController {

    @Autowired
    private ZBannerService zBannerService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String name) {
        Map<String, Object> map = new HashMap<>();
        Page<ZBanner> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZBanner> queryWrapper = new QueryWrapper<>();
        if (name != "" && name != null) {
            queryWrapper.eq("name", name);
        }
        IPage<ZBanner> iPage = zBannerService.selectAll(page, queryWrapper);
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
        QueryWrapper<ZBanner> queryWrapper = new QueryWrapper<>();
        ZBanner iPage = zBannerService.selectById(bid);
        map.put("data",iPage);
        return map;
//        return MessageBack.DATA(200,"",pageBean);
    }
    /**
     * 新增轮播图
     *
     * @param zBanner
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZBanner zBanner, @RequestParam(value = "imgFile", required = false) MultipartFile file) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                zBanner.setImgpath(null);
                if (zBanner.getImgpath() != null) {
                    String deletepath = zBanner.getImgpath().substring(1, zBanner.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    zBanner.setImgpath("/pictures/" + upload.get("filename"));
                }
            }
        }
        zBannerService.insert(zBanner);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改轮播图
     *
     * @param zBanner
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZBanner zBanner, @RequestParam(value = "imgFile", required = false) MultipartFile file) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                zBanner.setImgpath(null);
                if (zBanner.getImgpath() != null) {
                    String deletepath = zBanner.getImgpath().substring(1, zBanner.getImgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    zBanner.setImgpath("/pictures/" + upload.get("filename"));
                    ZBanner result = zBannerService.selectById(zBanner.getBid());
                    if (result.getImgpath() != null) {
                        UPLOAD.deleteFile(result.getImgpath());
                    }
                }
            }
        }
        zBannerService.update(zBanner);
        return MessageBack.MSG(200, "修改成功");
    }

    /**
     * 删除轮播图
     *
     * @param bid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(Integer bid) {
        zBannerService.delete(bid);
        return MessageBack.MSG(200, "删除成功");
    }
}
