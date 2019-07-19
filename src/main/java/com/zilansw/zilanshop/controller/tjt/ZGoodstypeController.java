package com.zilansw.zilanshop.controller.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.commons.PageBean;
import com.zilansw.zilanshop.commons.TreeParser;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * 查询所有商品分类菜单
     */
    @RequestMapping("selectType")
    @ResponseBody
    public Map<String, Object> selectType() {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<ZGoodstype> oneQueryWrapper = new QueryWrapper<>();
        oneQueryWrapper.eq("parentid", 0);
        //查询所有父级菜单
        List<ZGoodstype> oneList = zGoodstypeService.selectByPid(oneQueryWrapper);
        String onelevel = "";
        for (ZGoodstype one : oneList) {
            onelevel = onelevel + one.getGtypeid() + ",";
        }
        //查询所有二级菜单
        List<ZGoodstype> towList = zGoodstypeService.getByPid(onelevel.substring(0, (onelevel.length() - 1)));
        String twolevel = "";
        for (ZGoodstype tow : towList) {
            twolevel = twolevel + tow.getGtypeid() + ",";
        }
        //查询所有三级级菜单
        List<ZGoodstype> threeList = zGoodstypeService.getByPid(twolevel.substring(0, (twolevel.length() - 1)));
        map.put("oneList", oneList);
        map.put("towList", towList);
        map.put("threeList", threeList);
        return map;
    }


    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping("getById")
    @ResponseBody
    public Map<String, Object> getById(Integer gtypeid) {
        Map<String, Object> map = new HashMap<>();
        ZGoodstype one = zGoodstypeService.selectById(gtypeid);
        if (one != null) {
            if (one.getParentid() != 0) {//判断是否为二级菜单
                ZGoodstype two = zGoodstypeService.selectById(one.getParentid());
                if (two != null) {
                    if (two.getParentid() != 0) {//为三级菜单
                        ZGoodstype three = zGoodstypeService.selectById(two.getParentid());
                        if (three != null) {
                            map.put("three", two);
                            map.put("two", three);
                            map.put("data", one);
                        }
                    } else {
                        map.put("two", two);
                    }
                }
            } else {
                map.put("one", one);
            }
        }
        return map;
    }


    /**
     * 根据父级编号查询
     *
     * @return
     */
    @RequestMapping("selectByParentId")
    @ResponseBody
    public Map<String, Object> selectById(Integer parentid) {
        Map<String, Object> map = new HashMap<>();
        //查询列表数据
//        List<ZGoodstype> menuList = null;
        QueryWrapper<ZGoodstype> queryWrapper = new QueryWrapper<>();

        if (parentid != null) {
            queryWrapper.eq("parentid", parentid);
        } else {
            queryWrapper.eq("parentid", 0);
        }
        List<ZGoodstype> menuList = zGoodstypeService.selectByPid(queryWrapper);

//
//        menuList = zGoodstypeService.getAllMenuList(null,queryWrapper);
//        List<ZGoodstype> menus = TreeParser.getTreeList("0", menuList);
//        for (ZGoodstype str : menus) {
//            if (str.getChildren().size() != 0) {
//                for (ZGoodstype str1 : str.getChildren()) {
//                    if (str1.getChildren().size() != 0) {
//                        for (ZGoodstype str2 : str1.getChildren()) {
//                            if (str2.getChildren().size() != 0) {
//                            } else {
//                                str2.setChildren(null);
//                            }
//                        }
//                    } else {
//                        str1.setChildren(null);
//                    }
//                }
//            } else {
//                str.setChildren(null);
//            }
//        }
        map.put("data", menuList);
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
