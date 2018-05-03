package com.hongcheng.fruitmall.ucenter.dao.mapper;

import com.hongcheng.fruitmall.ucenter.pojo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

public interface UserEntityMapper {

    UserEntity selectById(Integer userId);

    int insert(UserEntity entity);

    int update(@Param("entity") UserEntity entity,@Param("id") Integer userId);

}
