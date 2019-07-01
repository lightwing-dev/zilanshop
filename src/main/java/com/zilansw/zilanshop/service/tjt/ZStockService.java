package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.dao.ZStockDao;
import com.zilansw.zilanshop.pojo.ZStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * 新增库存
     * @param zStock
     * @return
     */
    public int insert(ZStock zStock){
        return zStockDao.insert(zStock);
    }

    /**
     * 修改库存
     * @param zStock
     * @return
     */
    public int update(ZStock zStock){
        return zStockDao.updateById(zStock);
    }

    /**
     * 删除库存
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zStockDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public List<ZStock> selectAll(String gname, Integer pageIndex, Integer limit){
        return zStockDao.selectPage(gname,pageIndex,limit);
    }

    public ZStock selectById(Integer sid){
        return zStockDao.selectById(sid);
    }

    public int selectCount(String gname){
        QueryWrapper<ZStock> queryWrapper = new QueryWrapper<>();
        if (gname!="" && gname!=null){
            queryWrapper.eq("gname",gname);
        }
        return zStockDao.selectCount(queryWrapper);
    }

    /**
     * 根据商品编号查询库存
     * @param gid
     */
    public ZStock selectByGid(Long gid){
        return  zStockDao.selectByGid(gid);
    }

}
