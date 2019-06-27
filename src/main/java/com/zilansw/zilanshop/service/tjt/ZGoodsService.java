package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zilansw.zilanshop.dao.ZGoodsDao;
import com.zilansw.zilanshop.pojo.ZGoods;
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
public class ZGoodsService {

    @Autowired
    private ZGoodsDao zGoodsDao;

    /**
     * 新增商品
     * @param zGoods
     * @return
     */
    public int insert(ZGoods zGoods){
        return zGoodsDao.insert(zGoods);
    }

    /**
     * 修改商品
     * @param zGoods
     * @return
     */
    public int update(ZGoods zGoods){
        return zGoodsDao.updateById(zGoods);
    }

    /**
     * 删除商品
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zGoodsDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public List<ZGoods> selectAll(String gname, Integer pageIndex, Integer limit){
        return zGoodsDao.selectPage(gname,pageIndex,limit);
    }

    public int selectCount(String gname){
        QueryWrapper<ZGoods> queryWrapper = new QueryWrapper<>();
        if (gname!="" && gname!=null){
            queryWrapper.eq("gname",gname);
        }
        return zGoodsDao.selectCount(queryWrapper);
    }

}
