package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("z_stock")
public class ZStock {

  @TableId("sid")
  private long sid;
  private long gid;
  private long stocknum;


  public long getSid() {
    return sid;
  }

  public void setSid(long sid) {
    this.sid = sid;
  }


  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public long getStocknum() {
    return stocknum;
  }

  public void setStocknum(long stocknum) {
    this.stocknum = stocknum;
  }

}
