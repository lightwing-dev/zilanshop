package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("z_address")
public class ZAddress {

    @TableId("aid")
    private long aid;
    private long uid;
    private String name;
    private String province;
    private String city;
    private String district;
    private String address;
    private long isdefault;
    private String phone;


}
