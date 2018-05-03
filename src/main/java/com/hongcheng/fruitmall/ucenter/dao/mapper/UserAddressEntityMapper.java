package com.hongcheng.fruitmall.ucenter.dao.mapper;

import java.util.List;

import com.hongcheng.fruitmall.ucenter.pojo.entity.UserAddressEntity;
import org.apache.ibatis.annotations.Param;

public interface UserAddressEntityMapper {

    UserAddressEntity selectByUserId(Integer userId);

    int insert(UserAddressEntity entity);

    int update(@Param("address") UserAddressEntity address,@Param("uid") Integer userId);
}
