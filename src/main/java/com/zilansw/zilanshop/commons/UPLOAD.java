package com.zilansw.zilanshop.commons;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 */
public class UPLOAD
{

    public static Map<String,Object> UPLOADFILE(MultipartFile file)
    {


        Map<String,Object> map = new HashMap<>();
        if(file.isEmpty())
        {
            map.put("code",500);
            map.put("msg","文件为空");
            return map;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("文件名为"+fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
//        if(".jpg".toUpperCase().equals(suffixName.trim())||".png".toUpperCase().equals(suffixName.trim())){

        // 文件上传后的路径
        //服务器路径
        String filePath = Constant.UPLOADPATH;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        fileName=  UUID.randomUUID().toString().replace("-", "").toUpperCase()+suffixName;
        File dest = new File(filePath +fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);

            /**
             * 图片压缩开始
             */
            Thumbnails.of(filePath+fileName).scale(1f).outputQuality(1f).toFile(filePath+fileName);

            map.put("code",200);
            map.put("msg","文件上传成功");
            map.put("filename",fileName);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code",500);
            map.put("msg","上传失败");
        }
//        }
//        else
//        {
//            map.put("code",500);
//            map.put("msg","您上传的不是图片");
//        }
        return map;
    }


    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
