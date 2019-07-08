package com.zilansw.zilanshop.controller.ycc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zilansw.zilanshop.commons.MessageBack;
import com.zilansw.zilanshop.pojo.ZUser;
import com.zilansw.zilanshop.service.ycc.ZUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class ZUserController
{

    @Autowired
    private ZUserService service;

    /**
     * 获取用户列表
     * @param pageindex
     * @param pagesize
     * @return
     */
    @RequestMapping("/getList")
    public Map<String,Object> getList(@RequestParam(name = "pageindex",defaultValue = "1") Integer pageindex,@RequestParam(name = "pagesize",defaultValue = "20") Integer pagesize)
    {
        try
        {
            Page<ZUser> page = service.getUserList(pageindex, pagesize);
            return MessageBack.DATA(200,"用户列表获取成功",page);
        }
        catch (Exception e)
        {
            return MessageBack.MSG(500,"用户列表获取失败");
        }
    }

    /**
     * 通过OpenId获取用户信息
     * @param openid
     * @return
     */
    @RequestMapping("/getUserByOpenId")
    public Map<String,Object> getUserByOpenId(String openid)
    {
        try
        {
            ZUser data = service.getUserByOpenId(openid);
            return MessageBack.DATA(200,"用户数据获取成功",data);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"用户信息获取失败");
        }
    }

    /**
     * 修改用户信息
     * @param zUser
     * @param file
     * @return
     */
    @RequestMapping("/update")
    public Map<String,Object> updateUser(ZUser zUser, @RequestParam("imgFile") MultipartFile file)
    {
        try
        {
            service.updateUser(zUser);
            return MessageBack.MSG(200,"用户信息修改成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return MessageBack.MSG(500,"用户信息修改失败");
        }
    }
}
