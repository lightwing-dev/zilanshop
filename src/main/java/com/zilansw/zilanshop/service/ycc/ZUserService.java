package com.zilansw.zilanshop.service.ycc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZUserDao;
import com.zilansw.zilanshop.pojo.ZUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("ALL")
@Service
@Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED,rollbackFor = Exception.class,noRollbackFor = RuntimeException.class)
public class ZUserService
{
    @Autowired
    private ZUserDao dao;

    /**
     * 获取用户列表
     * @param pageindex
     * @param pagesize
     * @return
     */
    public Page<ZUser> getUserList(Integer pageindex,Integer pagesize)
    {
        pageindex=(pageindex-1)*pagesize;
        return (Page<ZUser>) dao.selectPage(
                new Page<ZUser>(pageindex,pagesize),
                new QueryWrapper<ZUser>()
        );
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Integer addUser(ZUser user)
    {
        return dao.insert(user);
    }

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    public Integer updateUser(ZUser user)
    {
        return dao.updateById(user);
    }
}
