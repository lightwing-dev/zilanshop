package com.zilansw.zilanshop.commons;

import lombok.Data;

import java.util.List;

/**
 *  分页类
 * @author Clarence
 */
@Data
public class PageBean
{
    /**
     * 当前页
     */
    private Integer pageindex;
    /**
     * 每页显示记录数
     */
    private Integer pagesize;
    /**
     * 上一页
     */
    private Integer prevpage;
    /**
     * 下一页
     */
    private Integer nextpage;
    /**
     * 总记录数
     */
    private Integer totalcount;
    /**
     * 总页数
     */
    private Integer pagecount;
    /**
     * 获取到的分页数据
     */
    private List<Object> list;

    public PageBean(Integer pageindex, Integer pagesize, Integer totalcount, List list) {
        this.pageindex = pageindex;
        this.pagesize = pagesize;
        this.totalcount = totalcount;
        this.list = list;

        this.pagecount=(totalcount%pagesize>0)?(totalcount/pagesize)+1:(totalcount/pagesize);
        this.prevpage=(pageindex-1<=0)?1:(pageindex-1);
        this.nextpage=(pageindex+1>=pagecount)?pagecount:(pageindex+1);
    }
    public PageBean(Integer pageindex, Integer pagesize, Integer totalcount) {
        this.pageindex = pageindex;
        this.pagesize = pagesize;
        this.totalcount = totalcount;

        this.pagecount=(totalcount%pagesize>0)?(totalcount/pagesize)+1:(totalcount/pagesize);
        this.prevpage=(pageindex-1<=0)?1:(pageindex-1);
        this.nextpage=(pageindex+1>=pagecount)?pagecount:(pageindex+1);
    }

}
