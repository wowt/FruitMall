package com.hongcheng.fruitmall.common.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询
 * Created by wanghongcheng on 2018/04/06.
 */
public class PageQuery {
    private Integer from;//查询起始行
    private Integer size=0; //查询行数
    private List<OrderBy> orderBy; //排序

    /**
     * 根据PageForm执行分页
     *
     * @param form
     */
    public void paging(PageForm form) {
        size = form.getSize() != null && form.getSize() > 0 ? form.getSize() : 0;
        from = form.getPage() != null && form.getPage() > 1 ? (form.getPage() - 1) * size : 0;

        //valid sort pattern should be like: +field,-field2,...
        if (null != form.getSort() && form.getSort().trim().length() > 0) {
            orderBy = new ArrayList<>();
            String[] sorts = form.getSort().split(",");
            for (String sort : sorts) {
                if (sort.startsWith("-")) {
                    orderBy.add(new OrderBy(sort.trim().substring(1), OrderBy.Dir.DESC));
                } else if (sort.startsWith("+")) {
                    orderBy.add(new OrderBy(sort.trim().substring(1), OrderBy.Dir.ASC));
                } else {
                    orderBy.add(new OrderBy(sort.trim()));
                }
            }
        }
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setOrderBy(List<OrderBy> orderBy) {
        this.orderBy = orderBy;
    }

    public List<OrderBy> getOrderBy() {
        return orderBy;
    }
}
