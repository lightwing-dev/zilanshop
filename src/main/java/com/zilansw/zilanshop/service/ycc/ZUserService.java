package com.zilansw.zilanshop.service.ycc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.dao.ZUserDao;
import com.zilansw.zilanshop.pojo.ZUser;
import org.omg.PortableInterceptor.INACTIVE;
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


    /**
     * 获取后台该openid的用户数量
     * @param openid
     * @return
     */
    public Integer getUserCountByOpenId(String openid)
    {
        ZUser zUser = new ZUser();
        zUser.setOpenid(openid);
        //实例化一个条件构造器
        QueryWrapper<ZUser> queryWrapper = new QueryWrapper<>();
        //判断openid作为条件
        queryWrapper.eq("openid",openid);
        //返回符合该openid的用户数量
        return dao.selectCount(queryWrapper);
    }

    /**
     * 删除用户
     * @param uid
     * @return
     */
    public Integer deleteUser(Integer uid)
    {
        return dao.deleteById(uid);
    }

    /**
     * 通过Openid获取用户信息
     * @param openid
     * @return
     */
    public ZUser getUserByOpenId(String openid)
    {
        ZUser zUser = new ZUser();
        zUser.setOpenid(openid);
        QueryWrapper<ZUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        return dao.selectOne(queryWrapper);
    }
}
