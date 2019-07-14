package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("z_cart")
@Data
public class ZCart {
    @TableId("cid")
    private Integer cid;
    private Integer gid;
    private Integer uid;
    private Integer num;
    private Date createTime;

    @TableField(exist = false)
    private ZUser zUser;

    @TableField(exist = false)
    private ZGoods zGoods;
}
