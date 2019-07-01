package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("z_message")
public class ZMessage implements Serializable {

  @TableId("mid")
  private long mid;
  private Date createTime;
  private String title;
  private String content;
  private String author;

}
