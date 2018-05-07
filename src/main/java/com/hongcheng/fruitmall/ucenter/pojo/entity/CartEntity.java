package com.hongcheng.fruitmall.ucenter.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;

    public CartEntity() {
    }

    public CartEntity(Integer userId, Integer productId) {
        this.userId = userId;
        this.productId = productId;
        this.addTime = LocalDateTime.now();
    }
}
