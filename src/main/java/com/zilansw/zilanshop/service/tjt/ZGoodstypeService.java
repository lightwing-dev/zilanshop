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

/**
 * @author tjt
 * @date 2019-06-27
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class ZGoodstypeService {

    @Autowired
    private ZGoodstypeDao zGoodstypeDao;

    /**
     * 新增商品类型
     * @param zGoodstype
     * @return
     */
    public int insert(ZGoodstype zGoodstype){
        return zGoodstypeDao.insert(zGoodstype);
    }

    /**
     * 修改商品类型
     * @param zGoodstype
     * @return
     */
    public int update(ZGoodstype zGoodstype){
        return zGoodstypeDao.updateById(zGoodstype);
    }

    /**
     * 删除商品类型
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zGoodstypeDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public IPage<ZGoodstype> selectAll(Page<ZGoodstype> page, QueryWrapper<ZGoodstype> queryWrapper){
        return zGoodstypeDao.selectPage(page,queryWrapper);
    }

    /**
     * 根据编号查询
     * @param gtypeid
     * @return
     */
    public ZGoodstype selectById(long gtypeid){
        return zGoodstypeDao.selectById(gtypeid);
    }

}
