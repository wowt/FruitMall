package com.hongcheng.fruitmall.mall.service;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.pojo.entity.HealthTalkEntity;
import com.hongcheng.fruitmall.mall.request.FruitRequest;

public interface FruitMallService {

    /**
     * 获取今日促销的水果列表
     * @return
     */
    PageList<FruitEntity> getDayDealFruit();

    /**
     * 店长推荐
     * @return
     */
    PageList<FruitEntity> getShopperPush();

    /**
     * 本周热卖
     * @return
     */
    PageList<FruitEntity> getWeekSaleFruits();

    /**
     * 按条件查询
     * @return
     */
    PageList<FruitEntity> getListByQuery(FruitRequest request);

    /**
     * 寄语
     */
    PageList<HealthTalkEntity> getTalkByRand();

    FruitEntity getById(Integer id);
}
