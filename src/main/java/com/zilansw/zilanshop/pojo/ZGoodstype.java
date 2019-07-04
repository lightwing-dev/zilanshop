package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zilansw.zilanshop.commons.TreeEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("z_goodstype")
public class ZGoodstype implements Serializable, TreeEntity<ZGoodstype> {

    private static final long serialVersionUID = 4456941123445965950L;

    @TableId("gtypeid")
    private long gtypeid;
    private String gtypename;
    private String iconimgpath;
    private String description;
    private Integer parentid;

    @TableField(exist = false)
    private List<ZGoodstype> nodes;

    @TableField(exist = false)
    private List<ZGoodstype> children;


    @Override
    public Object getValue() {
        return this.gtypeid;
    }

    @Override
    public Object getLabel() {
        return this.getGtypename();
    }

    @Override
    public void setChildList(List<ZGoodstype> childList) {

    }
}
