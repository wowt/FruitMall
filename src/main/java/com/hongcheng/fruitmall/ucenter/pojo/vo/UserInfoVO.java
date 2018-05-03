package com.hongcheng.fruitmall.ucenter.pojo.vo;

import com.hongcheng.fruitmall.ucenter.pojo.entity.UserAddressEntity;
import com.hongcheng.fruitmall.ucenter.pojo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoVO {

    private UserAddressEntity address;

    private UserEntity user;
}
