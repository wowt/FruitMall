package com.hongcheng.fruitmall.ucenter.dao.mapper;

import com.hongcheng.fruitmall.mall.pojo.vo.SimpleFruitInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface OrderProductRelationMapper {

    int insertBatch(@Param("list")Collection<SimpleFruitInfo> list,@Param("orId")Integer orderId);
}
