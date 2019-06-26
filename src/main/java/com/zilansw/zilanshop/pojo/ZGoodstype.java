package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("z_goodstype")
public class ZGoodstype {

  @TableId("gtypeid")
  private long gtypeid;
  private String gtypename;
  private String iconimgpath;
  private String description;

  /**
   * 商品列表
   */
  private List<ZGoods> goods;

  public List<ZGoods> getGoods() {
    return goods;
  }

  public void setGoods(List<ZGoods> goods) {
    this.goods = goods;
  }

  public long getGtypeid() {
    return gtypeid;
  }

  public void setGtypeid(long gtypeid) {
    this.gtypeid = gtypeid;
  }


  public String getGtypename() {
    return gtypename;
  }

  public void setGtypename(String gtypename) {
    this.gtypename = gtypename;
  }


  public String getIconimgpath() {
    return iconimgpath;
  }

  public void setIconimgpath(String iconimgpath) {
    this.iconimgpath = iconimgpath;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
