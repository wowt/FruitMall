package com.hongcheng.fruitmall.common.pojo;

/**
 * Created by wanghongcheng on 2018/04/06.
 */
public class OrderBy {
    /**
     * 排序顺序类型
     */
    public enum Dir {
        DESC, ASC
    }

    private String field; //排序字段
    private String dir; //顺序

    public OrderBy() {
    }

    public OrderBy(String field) {
        this.field = field;
        this.dir = Dir.ASC.name();
    }

    public OrderBy(String field, Dir dir) {
        this.field = field;
        this.dir = dir.name();
    }

    public static OrderBy field(String field) {
        return new OrderBy(field);
    }

    public OrderBy dir(String dir) {
        this.setDir(dir);
        return this;
    }

    public OrderBy dir(Dir dir) {
        this.setDir(dir.name());
        return this;
    }

    public static OrderBy of(String field, Dir dir) {
        return new OrderBy(field, dir);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
