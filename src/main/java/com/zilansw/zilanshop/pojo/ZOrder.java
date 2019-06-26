package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("z_order")
public class ZOrder {
  @TableId("orderid")
  private long orderid;
  private java.sql.Timestamp createTime;
  private long uid;
  private long status;
  private String remark;
  private String returnreason;
  private String rejectreason;
  private double totalprice;
  private double refundprice;
  private String province;
  private String city;
  private String district;
  private String address;
  private String name;
  private String phone;
  private String expnumber;

  private List<ZOrderdetail> orderdetails;

  public List<ZOrderdetail> getOrderdetails() {
    return orderdetails;
  }

  public void setOrderdetails(List<ZOrderdetail> orderdetails) {
    this.orderdetails = orderdetails;
  }

  public long getOrderid() {
    return orderid;
  }

  public void setOrderid(long orderid) {
    this.orderid = orderid;
  }


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }


  public long getStatus() {
    return status;
  }

  public void setStatus(long status) {
    this.status = status;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getReturnreason() {
    return returnreason;
  }

  public void setReturnreason(String returnreason) {
    this.returnreason = returnreason;
  }


  public String getRejectreason() {
    return rejectreason;
  }

  public void setRejectreason(String rejectreason) {
    this.rejectreason = rejectreason;
  }


  public double getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(double totalprice) {
    this.totalprice = totalprice;
  }


  public double getRefundprice() {
    return refundprice;
  }

  public void setRefundprice(double refundprice) {
    this.refundprice = refundprice;
  }


  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getDistrict() {
    return district;
  }

  public void setDistrict(String district) {
    this.district = district;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getExpnumber() {
    return expnumber;
  }

  public void setExpnumber(String expnumber) {
    this.expnumber = expnumber;
  }

}
