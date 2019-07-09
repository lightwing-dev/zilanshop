package com.zilansw.zilanshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZBanner;
import com.zilansw.zilanshop.pojo.ZGoodsimage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : tjt
 * 创建时间 : 2019-06-26
 */
@Mapper
public interface ZGoodsimageDao extends BaseMapper<ZGoodsimage> {
    int deleteByGid(@Param("gid") Integer gid);

    List<ZGoodsimage> selectByGid(@Param("gid") Long gid);

}
