package com.hongcheng.fruitmall.login.dao.mapper;


import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;

public interface LoginEntityMapper {

    LoginEntity getByEmail(String email);

    int update(LoginEntity entity);

    int setState(String email, String state);

    int insert(LoginEntity entity);

}
