package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

@TableName("z_goods")
@Data
public class ZGoods {

    @TableId("gid")
    private long gid;
    private long gtypeid;
    private String gname;
    private String gdetail;
    private double price;
    private Date createTime;
    private long status;

    private Integer salesVolume;

    @TableField(exist = false)
    private String img;
    /**
     * 商品类型
     */
    @TableField(exist = false)
    private ZGoodstype goodstype;

    /**
     * 商品库存
     */
    @TableField(exist = false)
    private ZStock stock;

    @TableField(exist = false)
    private List<ZEvaluation> zEvaluationList;

    @TableField(exist = false)
    private List<ZGoodsimage> zGoodsimage;
}
