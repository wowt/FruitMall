package com.hongcheng.fruitmall.common.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页列表
 * Created by wanghongcheng on 2018/04/05.
 */
public class PageList<T> {
    /**
     * 空列表
     */
    public final static PageList EMPTY_LIST = new PageList(0, new ArrayList());


    private long total; //总条数
    private List<T> data; //当前页数据集合


    public PageList() {
    }

    public PageList(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    /**
     * 分页
     *
     * @param data 需要进行内存分页的数据列表
     * @param page 页码，从1开始。默认：1
     * @param size 每页返回条数 默认：0
     * @return
     */
    public static <V> PageList<V> doPaging(List<V> data, int page, int size) {
        PageList<V> pager = new PageList<>();

        if (null == data || data.isEmpty()) {
            pager.total = 0;
            pager.data = new ArrayList<>();
        } else {
            pager.total = data.size();

            if (0 == size) {
                pager.data = new ArrayList<>();
            } else {
                if (page < 1) {
                    page = 1;
                }
                if (page - 1 > pager.total / size) {//页码已经超出了总页数，则返回空内容
                    pager.data = new ArrayList<>();
                } else {
                    pager.data = data.subList((page - 1) * size, page * size > pager.total ? (int) pager.total : page * size);
                }
            }
        }
        return pager;
    }
}
