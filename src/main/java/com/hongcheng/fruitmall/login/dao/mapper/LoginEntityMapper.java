package com.hongcheng.fruitmall.login.dao.mapper;


import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;
import org.apache.ibatis.annotations.Param;

public interface LoginEntityMapper {

    LoginEntity getByEmail(String email);

    LoginEntity getNActiveByEmail(String email);

    int update(LoginEntity entity);

    int setState(String email, String state);

    int insert(LoginEntity entity);

    LoginEntity getByUserId(@Param("userId") Integer userId);
}
