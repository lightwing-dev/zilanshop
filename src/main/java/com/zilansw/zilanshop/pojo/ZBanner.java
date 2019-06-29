package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("z_banner")
@Data
public class ZBanner {

  @TableId("bid")
  private long bid;
  private String imgpath;
  private String url;
  private long status;

}
