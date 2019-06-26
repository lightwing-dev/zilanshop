package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZAdminDao;
import com.zilansw.zilanshop.pojo.ZAdmin;
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
public class ZAdminService {

    @Autowired
    private ZAdminDao zAdminDao;

    /**
     * 新增管理员
     * @param zAdmin
     * @return
     */
    public int insert(ZAdmin zAdmin){
        return zAdminDao.insert(zAdmin);
    }

    /**
     * 修改管理员
     * @param zAdmin
     * @return
     */
    public int update(ZAdmin zAdmin){
        return zAdminDao.updateById(zAdmin);
    }

    /**
     * 删除管理员
     * @param aid
     * @return
     */
    public int delete(Integer aid){
        return zAdminDao.deleteById(aid);
    }

    /**
     * 查询所以/根据条件查询
     * @param
     * @return
     */
    public IPage<ZAdmin> selectAll(Page<ZAdmin> page, QueryWrapper<ZAdmin> queryWrapper){
        return zAdminDao.selectPage(page,queryWrapper);
    }


}
