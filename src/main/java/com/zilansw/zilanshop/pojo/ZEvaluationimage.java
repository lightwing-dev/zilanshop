package com.zilansw.zilanshop.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("z_evaluationimage")
public class ZEvaluationimage {

  @TableId("eiid")
  private long eiid;
  private String eid;
  private String imgpath;

  private Date createTime;

  public long getEiid() {
    return eiid;
  }

  public void setEiid(long eiid) {
    this.eiid = eiid;
  }


  public String getEid() {
    return eid;
  }

  public void setEid(String eid) {
    this.eid = eid;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getImgpath() {
    return imgpath;
  }

  public void setImgpath(String imgpath) {
    this.imgpath = imgpath;
  }

}
