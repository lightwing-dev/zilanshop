package com.zilansw.zilanshop.commons;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析树形数据工具类
 *
 */
public class TreeParser{
    /**
     * 解析树形数据
     * @param topId
     * @param entityList
     * @return
     * @author tjt
     */
    public static <E extends TreeEntity<E>> List<E> getTreeList(String topId, List<E> entityList) {
        List<E> resultList=new ArrayList<>();

        //获取顶层元素集合
        Object parentId;
        for (E entity : entityList) {
            parentId=entity.getParentid();
            if(parentId==null||topId.equals(parentId.toString())){
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (E entity : resultList) {
            entity.setChildList(getSubList(entity.getValue(),entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     * @param id
     * @param entityList
     * @return
     * @author tjt
     */
    private  static  <E extends TreeEntity<E>>  List<E> getSubList(Object id, List<E> entityList) {
        List<E> childList=new ArrayList<>();
        Object parentId;

        //子集的直接子对象
        for (E entity : entityList) {
            parentId=entity.getParentid();
            if(id.equals(parentId)){
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (E entity : childList) {
            entity.setChildList(getSubList(entity.getValue(), entityList));
        }

        //递归退出条件
        if(childList.size()==0){
            return null;
        }
        return childList;
    }
}
