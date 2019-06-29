package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.commons.UPLOAD;
import com.zilansw.zilanshop.pojo.ZGoodstype;
import com.zilansw.zilanshop.service.tjt.ZGoodstypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("admin/goodsType")
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
    public Map<String, Object> selectAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gtypename) {
        Map<String, Object> map = new HashMap<>();
        Page<ZGoodstype> page = new Page<>(pageIndex, limit);
        QueryWrapper<ZGoodstype> queryWrapper = new QueryWrapper<>();
        if (gtypename != "" && gtypename != null) {
            queryWrapper.eq("gtypename", gtypename);
        }
        IPage<ZGoodstype> iPage = zGoodstypeService.selectAll(page, queryWrapper);
        PageBean pageBean = new PageBean((int) iPage.getCurrent(), (int) iPage.getSize(), Integer.parseInt(String.valueOf(iPage.getTotal())));
        map.put("pageBean", pageBean);
        map.put("data", iPage.getRecords());
        return map;
    }

    /**
     * 根据编号查询
     *
     * @return
     */
    @RequestMapping("selectById")
    @ResponseBody
    public Map<String, Object> selectById(Integer gtypeid) {
        Map<String, Object> map = new HashMap<>();
        ZGoodstype iPage = zGoodstypeService.selectById(gtypeid);
        map.put("data", iPage);
        return map;
    }

    /**
     * 新增商品分类
     *
     * @param zGoodstype
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZGoodstype zGoodstype, @RequestParam(value = "imgFile", required = false) MultipartFile file) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                zGoodstype.setIconimgpath(null);
                if (zGoodstype.getIconimgpath() != null) {
                    String deletepath = zGoodstype.getIconimgpath().substring(1, zGoodstype.getIconimgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    zGoodstype.setIconimgpath("/pictures/" + upload.get("filename"));
                }
            }
        }
        zGoodstypeService.insert(zGoodstype);
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改商品分类
     *
     * @param zGoodstype
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZGoodstype zGoodstype, @RequestParam(value = "imgFile", required = false) MultipartFile file) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                zGoodstype.setIconimgpath(null);
                if (zGoodstype.getIconimgpath() != null) {
                    String deletepath = zGoodstype.getIconimgpath().substring(1, zGoodstype.getIconimgpath().length());
                    UPLOAD.deleteFile(deletepath);
                }
                Map<String, Object> upload = UPLOAD.UPLOADFILE(file);
                if ((int) upload.get("code") == 200) {
                    zGoodstype.setIconimgpath("/pictures/" + upload.get("filename"));
                    ZGoodstype result = zGoodstypeService.selectById(zGoodstype.getGtypeid());
                    if (result.getIconimgpath() != null) {
                        UPLOAD.deleteFile(result.getIconimgpath());
                    }
                }
            }
        }
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
