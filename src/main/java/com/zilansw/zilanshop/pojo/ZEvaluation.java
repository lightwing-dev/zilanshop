package com.zilansw.zilanshop.pojo;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

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
  private List<ZEvaluationimage> evaluationimages;

  private List<ZGoods> zGoods;

  private ZUser zUser;

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

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public long getGid() {
    return gid;
  }

  public void setGid(long gid) {
    this.gid = gid;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public List<ZEvaluationimage> getEvaluationimages() {
    return evaluationimages;
  }

  public void setEvaluationimages(List<ZEvaluationimage> evaluationimages) {
    this.evaluationimages = evaluationimages;
  }

  public List<ZGoods> getzGoods() {
    return zGoods;
  }

  public void setzGoods(List<ZGoods> zGoods) {
    this.zGoods = zGoods;
  }

  public ZUser getzUser() {
    return zUser;
  }

  public void setzUser(ZUser zUser) {
    this.zUser = zUser;
  }
}
