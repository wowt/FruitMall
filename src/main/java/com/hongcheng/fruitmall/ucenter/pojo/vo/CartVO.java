package com.hongcheng.fruitmall.ucenter.pojo.vo;

import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import com.hongcheng.fruitmall.ucenter.pojo.entity.CartEntity;
import lombok.Data;

import java.util.List;

@Data
public class CartVO extends CartEntity {

    private FruitEntity fruitsInfo;
}
