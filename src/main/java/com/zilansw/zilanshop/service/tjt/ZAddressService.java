package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZAddressDao;
import com.zilansw.zilanshop.pojo.ZAddress;
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
public class ZAddressService {

    @Autowired
    private ZAddressDao zAddressDao;

    /**
     * 新增收货地址
     * @param zAddress
     * @return
     */
    public int insert(ZAddress zAddress){
        return zAddressDao.insert(zAddress);
    }

    /**
     * 修改收货地址
     * @param zAddress
     * @return
     */
    public int update(ZAddress zAddress){
        return zAddressDao.updateById(zAddress);
    }

    /**
     * 删除收货地址
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zAddressDao.deleteById(aid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public IPage<ZAddress> selectAll(Page<ZAddress> page, QueryWrapper<ZAddress> queryWrapper){
        return zAddressDao.selectPage(page,queryWrapper);
    }


}
