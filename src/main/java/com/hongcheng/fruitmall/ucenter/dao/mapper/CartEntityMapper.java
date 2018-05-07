package com.hongcheng.fruitmall.ucenter.dao.mapper;

import com.hongcheng.fruitmall.ucenter.pojo.entity.CartEntity;
import com.hongcheng.fruitmall.ucenter.pojo.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartEntityMapper {

    CartEntity getById(Integer id);

    List<CartEntity> getByUserId(@Param("userId") Integer userId);

    int insert(CartEntity entity);

    List<CartVO> getCartInfoByUserId(@Param("userId") Integer userId);

    int deleteById(@Param("id") Integer cartId);

    int deleteByUserId(@Param("userId")Integer userId);

    Integer getCountByUserId(@Param("userId") Integer userId);
}
