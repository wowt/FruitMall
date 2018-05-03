package com.hongcheng.fruitmall.mall.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HealthTalkEntity {

    private Integer id;

    private String nutrition;

    private String talk;

    private String imgUrl;

    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
