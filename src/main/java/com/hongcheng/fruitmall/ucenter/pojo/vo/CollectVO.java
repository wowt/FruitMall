package com.hongcheng.fruitmall.ucenter.pojo.vo;

import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import com.hongcheng.fruitmall.ucenter.pojo.entity.UserCollectEntity;
import lombok.Data;

@Data
public class CollectVO extends UserCollectEntity {

    private SimpleFruitInfo fruitInfo;
}
