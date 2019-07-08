package com.zilansw.zilanshop.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ZGoodsimage implements Serializable {
    private static final long serialVersionUID = 4456941123445965950L;
    private Integer giid;
    private Integer gid;
    private String imgpath;
    private Date createTime;
}
