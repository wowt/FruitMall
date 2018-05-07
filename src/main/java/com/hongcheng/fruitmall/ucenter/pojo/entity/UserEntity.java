package com.hongcheng.fruitmall.ucenter.pojo.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hongcheng.fruitmall.ucenter.enums.UserState;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserEntity {

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 状态
     */
    private String userState;

    /**
     * 账号邮箱
     */
    private String email;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 头像地址
     */
    private String imgUrl;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 性别
     */
    private Integer userSex;

    /**
     * 当前积分
     */
    private Integer score;

    /**
     * 出生日期
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate borth;

    private String nick;

    public UserEntity(){}

    public UserEntity(String email) {
        this.setUserState(UserState.ACTIVE.getValue());
        this.setEmail(email);
    }
}
