package com.hongcheng.fruitmall.mall.dao.mapper;

import com.hongcheng.fruitmall.mall.pojo.entity.ProductTypeEntity;

public interface ProductTypeEntityMapper {

    ProductTypeEntity selectById(Integer id);

    int insert(ProductTypeEntity entity);

    int update(ProductTypeEntity entity);

}
