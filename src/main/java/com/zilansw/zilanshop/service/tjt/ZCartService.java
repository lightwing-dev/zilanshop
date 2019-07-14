package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.dao.ZCartDao;
import com.zilansw.zilanshop.dao.ZGoodsDao;
import com.zilansw.zilanshop.pojo.ZCart;
import com.zilansw.zilanshop.pojo.ZGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author tjt
 * @date 2019-07-13
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, noRollbackFor = RuntimeException.class)
public class ZCartService {

    @Autowired
    private ZCartDao zCartDao;

    /**
     * 新增購物車信息
     *
     * @param zCart
     * @return
     */
    public int insert(ZCart zCart) {
        return zCartDao.add(zCart);
    }

    /**
     * 删除商品
     *
     * @param queryWrapper
     * @return
     */
    public int delete(QueryWrapper<ZCart> queryWrapper) {
        return zCartDao.delete(queryWrapper);
    }

    /**
     * 查询所有/根据条件查询
     *
     * @param
     * @return
     */
    public List<ZCart> getList(Integer uid, Integer pageIndex, Integer limit) {
        return zCartDao.selectPage(uid, pageIndex, limit);
    }

    /**
     * 查询条数
     *
     * @param queryWrapper
     * @return
     */
    public int selectCount(QueryWrapper<ZCart> queryWrapper) {
        return zCartDao.selectCount(queryWrapper);
    }

    /**
     * 根据商品编号以及用户编号查询是否该用户已经将此商品加入购物车
     *
     * @param queryWrapper
     * @return
     */
    public ZCart getByGid(QueryWrapper<ZCart> queryWrapper) {
        return zCartDao.selectOne(queryWrapper);
    }

}
