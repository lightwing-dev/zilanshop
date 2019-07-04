package com.zilansw.zilanshop.commons;

import java.util.List;

/**
 * 树形数据实体接口
 * @param <E>
 */
public interface TreeEntity<E> {
    public Object getValue();
    public Object getParentid();
    public Object getLabel();
    public void setChildList(List<E> childList);
}
