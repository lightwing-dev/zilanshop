package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("z_orderdetail")
public class ZOrderdetail {

  @TableId("odid")
  private long odid;
  private long orderid;
  private long gid;
  private String num;
  private double price;
  private double totalprice;
  private ZGoods goods;

  public ZGoods getGoods() {
    return goods;
  }

  public void setGoods(ZGoods goods) {
    this.goods = goods;
  }

  public long getOdid() {
    return odid;
  }

  public void setOdid(long odid) {
    this.odid = odid;
  }


  public long getOrderid() {
    return orderid;
  }

  public void setOrderid(long orderid) {
    this.orderid = orderid;
  }


  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }


  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public double getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(double totalprice) {
    this.totalprice = totalprice;
  }

}
