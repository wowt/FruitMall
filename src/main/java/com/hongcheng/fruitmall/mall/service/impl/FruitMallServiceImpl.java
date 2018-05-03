package com.hongcheng.fruitmall.mall.service.impl;

import java.util.List;

import com.hongcheng.fruitmall.common.beanMapper.BeanMapperFactory;
import com.hongcheng.fruitmall.common.pojo.PageList;
import com.hongcheng.fruitmall.common.pojo.PageQuery;
import com.hongcheng.fruitmall.mall.dao.mapper.HealthTalkEntityMapper;
import com.hongcheng.fruitmall.mall.pojo.entity.HealthTalkEntity;
import com.hongcheng.fruitmall.mall.pojo.qo.FruitQO;
import com.hongcheng.fruitmall.mall.request.FruitRequest;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongcheng.fruitmall.mall.dao.cache.FruitMallCache;
import com.hongcheng.fruitmall.mall.dao.mapper.FruitEntityMapper;
import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.service.FruitMallService;

@Service
public class FruitMallServiceImpl implements FruitMallService {

    @Autowired
    private FruitEntityMapper fruitMapper;

    @Autowired
    private HealthTalkEntityMapper talkMapper;

    @Autowired
    private FruitMallCache cache;

    @Override
    public PageList<FruitEntity> getDayDealFruit() {
        List<FruitEntity> dayDeals = cache.getDayDealFromCache();
        if(CollectionUtils.isEmpty(dayDeals)) {
            dayDeals = fruitMapper.getDealFruits();
            cache.putDayDealToCache(dayDeals);
        }
        return new PageList<>(dayDeals.size(),dayDeals);
    }

    @Override
    public PageList<FruitEntity> getShopperPush() {
        List<FruitEntity> pushFruits = cache.getPushFruitsFromCache();
        if(CollectionUtils.isEmpty(pushFruits)) {
            pushFruits = fruitMapper.getPushFruits();
            cache.putPushFruitsToCache(pushFruits);
        }
        return new PageList<>(pushFruits.size(),pushFruits);
    }

    @Override
    public PageList<FruitEntity> getWeekSaleFruits() {
        List<FruitEntity> weekHots = cache.getWeekHotFruitsFromCache();
        if (CollectionUtils.isEmpty(weekHots)) {
            weekHots = fruitMapper.getHots();
            cache.putWeekHotFruitsToCache(weekHots);
        }
        return new PageList<>(weekHots.size(),weekHots);
    }

    @Override
    public PageList<FruitEntity> getWillOnSale() {
        List<FruitEntity> willSaleFruits = cache.getWillSaleFruitsFromCache();
        if(CollectionUtils.isEmpty(willSaleFruits)) {
            willSaleFruits = fruitMapper.getWillSaleFruits();
            cache.putWillSaleFruitsToCache(willSaleFruits);
        }
        return new PageList<>(willSaleFruits.size(),willSaleFruits);
    }

    @Override
    public PageList<FruitEntity> getListByQuery(FruitRequest request) {
        FruitQO qo = createQo(request);
        return new PageList<>(fruitMapper.countByQO(qo),fruitMapper.getListByQO(qo));
    }

    @Override
    public HealthTalkEntity getOneTalkByRand() {
        return talkMapper.selectOne();
    }

    @Override
    public FruitEntity getById(Integer id) {
        return fruitMapper.getById(id);
    }

    private FruitQO createQo(FruitRequest request) {
        FruitQO qo = BeanMapperFactory.getMapperFacade().map(request, FruitQO.class);
        qo.paging(request);
        return qo;
    }
}
