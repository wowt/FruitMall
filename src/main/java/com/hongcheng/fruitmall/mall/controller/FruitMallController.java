package com.hongcheng.fruitmall.mall.controller;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.mall.pojo.entity.HealthTalkEntity;
import com.hongcheng.fruitmall.mall.request.FruitRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.service.FruitMallService;

@RestController
@RequestMapping("/mall/v1/fruits")
public class FruitMallController {

    @Autowired
    private FruitMallService mallService;

    @GetMapping("/dayDeals")
    public RestResponse<PageList<FruitEntity>> getDayDeals() {
        return RestResponse.success(mallService.getDayDealFruit());
    }

    @GetMapping("/shopper-push")
    public RestResponse<PageList<FruitEntity>> getPushFruits() {
        return RestResponse.success(mallService.getShopperPush());
    }

    @GetMapping("/week-hot")
    public RestResponse<PageList<FruitEntity>> getWeekHots() {
        return RestResponse.success(mallService.getWeekSaleFruits());
    }

    @GetMapping("/will-sale")
    public RestResponse<PageList<FruitEntity>> getWills() {
        return RestResponse.success(mallService.getWillOnSale());
    }

    @GetMapping("/health-talk")
    public RestResponse<HealthTalkEntity> getTalk() {
        return RestResponse.success(mallService.getOneTalkByRand());
    }

    @GetMapping("/search")
    public RestResponse<PageList<FruitEntity>> getBySearch(FruitRequest request) {
        return RestResponse.success(mallService.getListByQuery(request));
    }

    @GetMapping("/fruit/{id}")
    public RestResponse<FruitEntity> getById(@PathVariable Integer id) {
        return RestResponse.success(mallService.getById(id));
    }
}
