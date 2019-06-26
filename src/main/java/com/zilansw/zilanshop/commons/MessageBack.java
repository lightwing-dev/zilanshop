package com.zilansw.zilanshop.commons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageBack
{
    public static Map<String,Object> MSG(Integer code, String message)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",message);
        return map;
    }


    public static Map<String,Object> DATA(Integer code, String message,Object data)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",message);
        map.put("data",data);
        return map;
    }

    /**
     * 文件上传失败
     * @param statuscode
     * @param message
     * @return
     */
    public static Map<String,Object> uploadFailed(Integer statuscode,String message)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("errno",statuscode);
        map.put("msg",message);
        return map;
    }

    /**
     * 文件上传成功
     * @param statuscode
     * @param message
     * @return
     */
    public static Map<String,Object> uploadSuccess(Integer statuscode, String message, List<String> list)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("errno",statuscode);
        map.put("data",list);
        map.put("msg",message);
        return map;
    }

}
