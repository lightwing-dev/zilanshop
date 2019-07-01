package com.zilansw.zilanshop.service.tjt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZMessageDao;
import com.zilansw.zilanshop.pojo.ZMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author tjt
 * @date 2019-07-01
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class ZMessageService {

    @Autowired
    private ZMessageDao zMessageDao;

    /**
     * 新增消息公告
     * @param zMessage
     * @return
     */
    public int insert(ZMessage zMessage){
        return zMessageDao.insert(zMessage);
    }

    /**
     * 修改消息公告
     * @param zMessage
     * @return
     */
    public int update(ZMessage zMessage){
        return zMessageDao.updateById(zMessage);
    }

    /**
     * 删除消息公告
     * @param mid
     * @return
     */
    public int delete(Integer mid){
        return zMessageDao.deleteById(mid);
    }

    /**
     * 查询所有/根据条件查询
     * @param
     * @return
     */
    public IPage<ZMessage> selectAll(Page<ZMessage> page, QueryWrapper<ZMessage> queryWrapper){
        return zMessageDao.selectPage(page,queryWrapper);
    }

    /**
     * 根据编号查询
     * @param bid
     * @return
     */
    public ZMessage selectById(Long bid){
        return zMessageDao.selectById(bid);
    }

}
