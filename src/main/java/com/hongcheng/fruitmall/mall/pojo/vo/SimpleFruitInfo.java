package com.hongcheng.fruitmall.mall.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SimpleFruitInfo {

    private Integer productId;

    private String productImg;

    private String title;

    private BigDecimal dealPrice;

    private Integer quantity;
}
