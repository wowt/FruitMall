package com.hongcheng.fruitmall.appointment.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentVO {

    /**
     * 产品编号
     */
    private Integer productId;

    /**
     * 产品名字
     */
    private String productName;

    /**
     * 图片URL
     */
    private String productImg;

    /**
     * 开始销售时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate startTime;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 标题
     */
    private String title;

    /**
     * 预约人数
     */
    private Integer peopleNum;

    /**
     * 是否已经订阅了
     */
    private Boolean hasSub;
}
