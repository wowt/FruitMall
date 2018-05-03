package com.hongcheng.fruitmall.mall.request;

import com.hongcheng.fruitmall.common.pojo.PageForm;
import lombok.Data;

@Data
public class FruitRequest extends PageForm {

    private Integer productType;

    private String productName;

}
