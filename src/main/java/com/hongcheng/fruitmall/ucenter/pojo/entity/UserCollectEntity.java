package com.hongcheng.fruitmall.ucenter.pojo.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UserCollectEntity {

    /**
     * 唯一标示
     */
    private Integer collectId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 收藏时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
}
