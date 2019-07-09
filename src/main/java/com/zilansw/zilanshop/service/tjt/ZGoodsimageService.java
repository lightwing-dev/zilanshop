package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZGoodsimageDao;
import com.zilansw.zilanshop.pojo.ZGoodsimage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tjt
 * @date 2019-07-05
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class ZGoodsimageService {

    @Autowired
    private ZGoodsimageDao zGoodsimageDao;

    /**
     * 新增商品图片
     *
     * @param zGoodsimage
     * @return
     */
    public int insert(ZGoodsimage zGoodsimage) {
        return zGoodsimageDao.insert(zGoodsimage);
    }

    /**
     * 修改商品图片
     *
     * @param zGoodsimage
     * @return
     */
    public int update(ZGoodsimage zGoodsimage) {
        return zGoodsimageDao.updateById(zGoodsimage);
    }

    /**
     * 删除商品图片
     *
     * @param aid
     * @return
     */
    public int delete(Integer aid) {
        return zGoodsimageDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     *
     * @param
     * @return
     */
    public IPage<ZGoodsimage> selectAll(Page<ZGoodsimage> page, QueryWrapper<ZGoodsimage> queryWrapper) {
        return zGoodsimageDao.selectPage(page, queryWrapper);
    }

    /**
     * 根据编号查询
     *
     * @param gid
     * @return
     */
    public List<ZGoodsimage> selectByGid(Long gid) {
        return zGoodsimageDao.selectByGid(gid);
    }

    /**
     * 根据商品编号删除商品图片
     *
     * @param gid
     * @return
     */
    public int deleteByGid(Integer gid) {
        return zGoodsimageDao.deleteByGid(gid);
    }
}
