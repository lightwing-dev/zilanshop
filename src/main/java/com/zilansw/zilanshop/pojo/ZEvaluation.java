package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;

@TableName("z_evaluation")
public class ZEvaluation {

  private long eid;
  private double star;
  private String content;

  /**
   * 评论图片
   */
  private List<ZEvaluationimage> evaluationimages;

  public List<ZEvaluationimage> getEvaluationimages() {
    return evaluationimages;
  }

  public void setEvaluationimages(List<ZEvaluationimage> evaluationimages) {
    this.evaluationimages = evaluationimages;
  }

  public long getEid() {
    return eid;
  }

  public void setEid(long eid) {
    this.eid = eid;
  }


  public double getStar() {
    return star;
  }

  public void setStar(double star) {
    this.star = star;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
