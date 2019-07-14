package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
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

    private ZUser zUser;
}
