package com.hongcheng.fruitmall.login.pojo.entity;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class LoginEntity {

    private Integer id;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 登录账号
     */
    private String email;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registTime;

    /**
     * 最后一次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime lastTime;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 状态：待激活、已激活
     */
    private String state;

}
