package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.commons.UPLOAD;
import com.zilansw.zilanshop.pojo.ZGoods;
import com.zilansw.zilanshop.pojo.ZGoodsimage;
import com.zilansw.zilanshop.service.tjt.ZGoodsService;
import com.zilansw.zilanshop.service.tjt.ZGoodsimageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Controller
@RequestMapping("admin/zGoods")
public class ZGoodsController {

    @Autowired
    private ZGoodsService zGoodsService;

    @Autowired
    private ZGoodsimageService zGoodsimageService;

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getList")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer limit, String gname) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<ZGoods> queryWrapper = new QueryWrapper<>();
        List<ZGoods> iPage = zGoodsService.getList(gname, ((pageIndex - 1) * limit), limit);
        if (gname != "" && gname != null) {
            queryWrapper.eq("gname", gname);
        }
        PageBean pageBean = new PageBean(pageIndex, limit, zGoodsService.selectCount(queryWrapper));
        map.put("data", iPage);
        map.put("pageBean", pageBean);
        return map;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public Map<String, Object> selectAll() {
        Map<String, Object> map = new HashMap<>();
        List<ZGoods> iPage = zGoodsService.selectAll();
        map.put("data", iPage);
        return map;
    }

    /**
     * 根据编号查询
     *
     * @return
     */
    @RequestMapping("selectById")
    @ResponseBody
    public Map<String, Object> selectById(Integer gid) {
        Map<String, Object> map = new HashMap<>();
        ZGoods zGoods = zGoodsService.selectById(gid);
        map.put("data", zGoods);
        return map;
    }

    /**
     * 新增商品
     *
     * @param zGoods
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> insert(ZGoods zGoods, @RequestParam(value = "imgFile", required = false) MultipartFile[] file) {
        ZGoodsimage zGoodsimage = new ZGoodsimage();
        zGoods.setCreateTime(new Date());
        int j = zGoodsService.insertGood(zGoods);
        if (j > 0) {
            for (int i = 0; i < file.length; i++) {
                MultipartFile files = file[i];
                if (files != null) {
                    if (StringUtils.isNotBlank(files.getOriginalFilename())) {
                        zGoodsimage.setImgpath(null);
                        if (zGoodsimage.getImgpath() != null) {
                            String deletepath = zGoodsimage.getImgpath().substring(1, zGoodsimage.getImgpath().length());
                            UPLOAD.deleteFile(deletepath);
                        }
                        Map<String, Object> upload = UPLOAD.UPLOADFILE(files);
                        if ((int) upload.get("code") == 200) {
                            zGoodsimage.setImgpath("/pictures/" + upload.get("filename"));
                            zGoodsimage.setGid(Integer.parseInt(String.valueOf(zGoods.getGid())));
                            zGoodsimage.setCreateTime(new Date());
                            zGoodsimageService.insert(zGoodsimage);
                        }
                    }
                }
            }
        }
        return MessageBack.MSG(200, "新增成功");
    }

    /**
     * 修改商品
     *
     * @param zGoods
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> update(ZGoods zGoods, @RequestParam(value = "imgFile", required = false) MultipartFile[] file) {
        ZGoodsimage zGoodsimage = new ZGoodsimage();
        List<ZGoodsimage> result = zGoodsimageService.selectByGid(Long.valueOf(zGoods.getGid()));
        if (file.length>0){
            zGoodsimageService.deleteByGid(Integer.valueOf(Long.valueOf(zGoods.getGid()).toString()));
        }
        int j = zGoodsService.update(zGoods);
        if (j > 0) {
            for (int i = 0; i < file.length; i++) {
                MultipartFile files = file[i];
                if (files != null) {
                    if (StringUtils.isNotBlank(files.getOriginalFilename())) {
                        zGoodsimage.setImgpath(null);
                        if (zGoodsimage.getImgpath() != null) {
                            String deletepath = zGoodsimage.getImgpath().substring(1, zGoodsimage.getImgpath().length());
                            UPLOAD.deleteFile(deletepath);
                        }
                        Map<String, Object> upload = UPLOAD.UPLOADFILE(files);
                        if ((int) upload.get("code") == 200) {
                            zGoodsimage.setCreateTime(new Date());
                            zGoodsimage.setImgpath("/pictures/" + upload.get("filename"));
                            if (result.size() != 0) {
                                for (ZGoodsimage str : result) {
                                    UPLOAD.deleteFile(str.getImgpath());
                                }
                            }
                            zGoodsimage.setGid(Integer.parseInt(Long.valueOf(zGoods.getGid()).toString()));
                            zGoodsimageService.insert(zGoodsimage);
                        }
                    }
                }
            }
        }

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
