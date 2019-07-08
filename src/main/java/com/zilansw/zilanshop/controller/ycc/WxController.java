package com.zilansw.zilanshop.controller.ycc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zilansw.zilanshop.commons.Constant;
import com.zilansw.zilanshop.commons.HttpClient3;
import com.zilansw.zilanshop.commons.WxPayConfig;
import com.zilansw.zilanshop.pojo.ZUser;
import com.zilansw.zilanshop.service.ycc.ZUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信公众号相关
 */
@RestController
@RequestMapping("/wechat")
public class WxController
{
    @Autowired
    private ZUserService service;

    /**
     * 微信登录认证
     * @return
     */
    @RequestMapping("/getAuth")
    public String getAuth()
    {
        WxPayConfig config = new WxPayConfig();
        String REDURL= Constant.HDURL+"/wechat/getInfo";
        return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ config.getAppID() +"&redirect_uri="+REDURL+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    }

    /**
     * 获取微信用户个人信息
     * @return
     */
    @RequestMapping("/getInfo")
    public String getInfo(String code, HttpSession session)
    {
        WxPayConfig config = new WxPayConfig();
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        String data= HttpClient3.doGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+config.getAppID()+"&secret="+config.getAppSecret()+"&code="+code+"&grant_type=authorization_code");
        map.put("data",data);

        session.setAttribute("WECHATINFO",data);

        JSONObject wxData = JSON.parseObject(data);


        /**
         * 判断数据库内是否有该微信用户信息,若无该用户信息则新增用户。
         */
        Integer hasReg = service.getUserCountByOpenId(wxData.getString("openid"));
        if(hasReg!=0)
        {

        }
        else
        {
            Map<String,Object> getWechatProfileData = new HashMap<>();
            getWechatProfileData.put("access_token",wxData.getString("access_token"));
            getWechatProfileData.put("openid",wxData.getString("openid"));
            getWechatProfileData.put("lang","zh_CN");
            String wechatprofile = HttpClient3.doPost("https://api.weixin.qq.com/sns/userinfo",getWechatProfileData);
            JSONObject profile = JSONObject.parseObject(wechatprofile);

            ZUser user = new ZUser();
            user.setOpenid(profile.getString("openid"));
            user.setNickname(profile.getString("nickname"));
            user.setHeadimg(profile.getString("headimgurl"));

            //注册一个新的微信用户
            service.addUser(user);
        }

        /**
         * 操作完成后保存SESSION跳转至商城首页
         */
        return "redirect:/wechat/mainpage";
    }
}
