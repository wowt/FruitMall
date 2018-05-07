package com.hongcheng.fruitmall.ucenter.dao.mapper;

import java.util.List;

import com.hongcheng.fruitmall.common.pojo.PageQuery;
import com.hongcheng.fruitmall.ucenter.pojo.entity.OrderEntity;
import com.hongcheng.fruitmall.ucenter.pojo.vo.OrderVO;
import org.apache.ibatis.annotations.Param;

public interface OrderEntityMapper {

    OrderEntity selectByOrderId(Integer orderId);

    List<OrderEntity> selectByUserId(Integer userId);

    List<OrderVO> selectDetailByUserId(@Param("userId")Integer userId, @Param("qo")PageQuery qo);

    Integer getCountByUserId(@Param("userId") Integer userId);

    int insert(OrderEntity orderEntity);

    int signOrderById(@Param("orderId") Integer orderId);
}
