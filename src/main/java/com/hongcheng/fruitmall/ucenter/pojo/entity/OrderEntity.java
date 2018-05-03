package com.hongcheng.fruitmall.ucenter.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author wanghongcheng 2018/04/18
 * 订单实体类
 */
@Data
public class OrderEntity {

    private Integer orderId;

    private BigDecimal payMoney;

    private String payPath;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signTime;

    private String state;

    private String remarks;

    private Integer userId;

    private String userAddress;
}
