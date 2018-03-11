package com.hongcheng.fruitmall.mall.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CartEntity {
    /**
     * 购物车编号
     */
    private Integer cartId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 商品列表
     */
    private Integer productId;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    public CartEntity() {
    }

    public CartEntity(Integer userId) {
        this.userId = userId;
    }
}
