package com.zilansw.zilanshop.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zilansw.zilanshop.pojo.ZGoodstype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : tjt
 * 创建时间 : 2019-06-27
 */
@Mapper
public interface ZGoodstypeDao extends BaseMapper<ZGoodstype> {
    List<ZGoodstype> getByPid(@Param("pid") Object pid);

}