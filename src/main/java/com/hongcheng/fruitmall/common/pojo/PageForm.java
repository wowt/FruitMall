package com.hongcheng.fruitmall.common.pojo;

import java.io.Serializable;

public class PageForm implements Serializable {
    private Integer page;// 页码
    private Integer size;// 本页返回条数
    private String sort; //排序语句


    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 设置分页默认值。 即当前端没有传分页参数的时候，可以调用次方法设置分页默认值
     *
     * @param page 页码
     * @param size 每页返回条数
     */
    public void setDefaults(int page, int size) {
        if (this.page == null) {
            this.page = page;
        }
        if (this.size == null) {
            this.size = size;
        }
    }

    /**
     * 设置分页和排序默认值。即当前端没有传分页参数的时候，可以调用次方法设置分页默认值
     *
     * @param page 页码
     * @param size 每页返回条数
     * @param sort 排序语句。语句中，多字段排序用逗号隔开；对于一个字段，+号表示升序，-号表示降序。 如：+field1,-field2,...
     */
    public void setDefaults(int page, int size, String sort) {
        this.setDefaults(page, size);
        if (this.sort == null || this.sort.trim().isEmpty()) {
            this.sort = sort;
        }
    }
}
