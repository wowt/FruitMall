package com.hongcheng.fruitmall.ucenter.pojo.vo;

import com.hongcheng.fruitmall.common.pojo.SimpleUserInfo;
import com.hongcheng.fruitmall.ucenter.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import lombok.Data;

import java.util.List;

/**
 * 返回数据对象
 */
@Data
public class OrderVO extends OrderEntity {

    private List<SimpleFruitInfo> fruitsInfo;

    private SimpleUserInfo userInfo;

}
