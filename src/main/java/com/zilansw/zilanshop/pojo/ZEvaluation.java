package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@TableName("z_evaluation")
public class ZEvaluation {

  private long eid;
  private double star;
  private String content;

  private long uid;

  private long gid;

  private Date createTime;
  /**
   * 评论图片
   */
  @TableField(exist = false)
  private List<ZEvaluationimage> evaluationimages;

  @TableField(exist = false)
  private ZGoods zGoods;

  @TableField(exist = false)
  private ZUser zUser;

}
