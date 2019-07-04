package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZGoodstypeDao;
import com.zilansw.zilanshop.pojo.ZGoodstype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tjt
 * @date 2019-06-27
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class ZGoodstypeService {

    @Autowired
    private ZGoodstypeDao zGoodstypeDao;

    /**
     * 新增商品类型
     *
     * @param zGoodstype
     * @return
     */
    public int insert(ZGoodstype zGoodstype) {
        return zGoodstypeDao.insert(zGoodstype);
    }

    /**
     * 修改商品类型
     *
     * @param zGoodstype
     * @return
     */
    public int update(ZGoodstype zGoodstype) {
        return zGoodstypeDao.updateById(zGoodstype);
    }

    /**
     * 删除商品类型
     *
     * @param aid
     * @return
     */
    public int delete(Integer aid) {
        return zGoodstypeDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     *
     * @param
     * @return
     */
    public IPage<ZGoodstype> selectAll(Page<ZGoodstype> page, QueryWrapper<ZGoodstype> queryWrapper) {
        return zGoodstypeDao.selectPage(page, queryWrapper);
    }

    /**
     * 根据编号查询
     *
     * @param gtypeid
     * @return
     */
    public ZGoodstype selectById(long gtypeid) {
        return zGoodstypeDao.selectById(gtypeid);
    }


    /**
     * 获取所有商品分类列表
     */
    public List<ZGoodstype> getAllMenuList(List<Long> menuIdList) {
        QueryWrapper<ZGoodstype> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parentid", 0);
        //查询根菜单列表
        List<ZGoodstype> menuList = zGoodstypeDao.selectList(queryWrapper);
        //递归获取子菜单
        List<ZGoodstype> result =  getMenuTreeList(menuList, menuIdList);
        return result;
    }
    public List<ZGoodstype> getMenuTreeList(List<ZGoodstype> menuList, List<Long> menuIdList) {
        List<ZGoodstype> subMenuList = new ArrayList<ZGoodstype>();

        for (ZGoodstype sysMenu : menuList) {
                sysMenu.setChildren(getMenuTreeList(queryListParentId(sysMenu.getGtypeid(), menuIdList), menuIdList));
            subMenuList.add(sysMenu);
        }

        return subMenuList;
    }

    public List<ZGoodstype> queryListParentId(Long parentId, List<Long> menuIdList) {
        QueryWrapper<ZGoodstype> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parentid", parentId);
        List<ZGoodstype> menuList = zGoodstypeDao.selectList(queryWrapper);
        if (menuIdList == null) {
            return menuList;
        }

        List<ZGoodstype> userMenuList = new ArrayList<>();
        for (ZGoodstype menu : menuList) {
            if (menuIdList.contains(menu.getGtypeid())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }
}
