package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZBannerDao;
import com.zilansw.zilanshop.pojo.ZBanner;
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
public class ZBannerService {

    @Autowired
    private ZBannerDao zBannerDao;

    /**
     * 新增轮播图
     * @param zBanner
     * @return
     */
    public int insert(ZBanner zBanner){
        return zBannerDao.insert(zBanner);
    }

    /**
     * 修改轮播图
     * @param zBanner
     * @return
     */
    public int update(ZBanner zBanner){
        return zBannerDao.updateById(zBanner);
    }

    /**
     * 删除轮播图
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zBannerDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public IPage<ZBanner> selectAll(Page<ZBanner> page, QueryWrapper<ZBanner> queryWrapper){
        return zBannerDao.selectPage(page,queryWrapper);
    }

    /**
     * 根据编号查询
     * @param bid
     * @return
     */
    public ZBanner selectById(Long bid){
        return zBannerDao.selectById(bid);
    }

}
