package com.hongcheng.fruitmall.mall.dao.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import com.hongcheng.fruitmall.mall.pojo.qo.FruitQO;
import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

public interface FruitEntityMapper {

    FruitEntity selectById(Integer id);

    int insert(FruitEntity entity);

    int update(FruitEntity entity);

    List<FruitEntity> getDealFruits();

    List<FruitEntity> getPushFruits();

    @MapKey("productId")
    Map<Integer,SimpleFruitInfo> getBatchByIds(@Param("ids")Collection<Integer> ids);

    List<FruitEntity> getListByQO(@Param("qo") FruitQO qo);

    int countByQO(@Param("qo") FruitQO qo);

    FruitEntity getById(@Param("id") Integer id);

    List<FruitEntity> getHots();
}
