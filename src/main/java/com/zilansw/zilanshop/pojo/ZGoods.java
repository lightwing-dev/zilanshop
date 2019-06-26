package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("z_goods")
public class ZGoods {

  @TableId("gid")
  private long gid;
  private long gtypeid;
  private String gname;
  private String gdetail;
  private double price;
  private java.sql.Timestamp createTime;
  private long status;

  /**
   * 商品类型
   */
  private ZGoodstype goodstype;

  /**
   * 商品库存
   */
  private ZStock stock;

  public ZStock getStock() {
    return stock;
  }

  public void setStock(ZStock stock) {
    this.stock = stock;
  }

  public ZGoodstype getGoodstype() {
    return goodstype;
  }

  public void setGoodstype(ZGoodstype goodstype) {
    this.goodstype = goodstype;
  }

  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public long getGtypeid() {
    return gtypeid;
  }

  public void setGtypeid(long gtypeid) {
    this.gtypeid = gtypeid;
  }


  public String getGname() {
    return gname;
  }

  public void setGname(String gname) {
    this.gname = gname;
  }


  public String getGdetail() {
    return gdetail;
  }

  public void setGdetail(String gdetail) {
    this.gdetail = gdetail;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }

}
