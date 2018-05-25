package com.lipiao.readhub.me.bean;

import java.util.List;

/**
 * @author lipiao
 * @data 2018/5/25.
 * @description 所有数据的统一格式bean
 */
public class BaseData<T> {
    private int pageSize;
    private int totalItems;
    private int totalPages;
    public List<T> data;

    @Override
    public String toString() {
        return "BaseData{" +
                "pageSize=" + pageSize +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                ", data=" + data +
                '}';
    }
}
