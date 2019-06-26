package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZStockDao;
import com.zilansw.zilanshop.pojo.ZStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tjt
 * @date 2019-06-26
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class ZStockService {

    @Autowired
    private ZStockDao zStockDao;

    /**
     * 新增轮播图
     * @param zStock
     * @return
     */
    public int insert(ZStock zStock){
        return zStockDao.insert(zStock);
    }

    /**
     * 修改轮播图
     * @param zStock
     * @return
     */
    public int update(ZStock zStock){
        return zStockDao.updateById(zStock);
    }

    /**
     * 删除轮播图
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zStockDao.deleteById(aid);
    }

    /**
     * 查询所以/根据条件查询
     * @param
     * @return
     */
    public IPage<ZStock> selectAll(Page<ZStock> page, QueryWrapper<ZStock> queryWrapper){
        return zStockDao.selectPage(page,queryWrapper);
    }


}
