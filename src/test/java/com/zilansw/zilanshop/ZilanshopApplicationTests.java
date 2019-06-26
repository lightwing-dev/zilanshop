package com.zilansw.zilanshop;

import com.alibaba.fastjson.JSON;
import com.zilansw.zilanshop.pojo.ZUser;
import com.zilansw.zilanshop.service.ycc.ZUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZilanshopApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private ZUserService service;

    @Test
    public void getUserList()
    {
        System.out.println(JSON.toJSONString(service.getUserList(1,10)));
    }


    @Test
    public void addUser()
    {
        ZUser user = new ZUser();
        user.setUsername("test");
        user.setPassword("test");
        user.setOpenid("wxidxxxxxxx");
        service.addUser(user);
    }

    @Test
    public void updateUser()
    {
        ZUser user = new ZUser();
        user.setUid(1);
        user.setOpenid("openid");
        service.updateUser(user);
    }

}
