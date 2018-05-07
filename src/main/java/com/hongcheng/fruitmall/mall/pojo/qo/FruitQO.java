package com.hongcheng.fruitmall.mall.pojo.qo;

import com.hongcheng.fruitmall.common.pojo.PageQuery;
import lombok.Data;

@Data
public class FruitQO extends PageQuery {

    private Integer productType;

    private String name;
}
