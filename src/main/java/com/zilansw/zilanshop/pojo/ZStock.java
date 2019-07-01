package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("z_stock")
@Data
public class ZStock {

  @TableId("sid")
  private long sid;
  private long gid;
  private long stocknum;

  @TableField(exist = false)
  private ZGoods zGoods;

}
