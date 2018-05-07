package com.hongcheng.fruitmall.appointment.dao.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.mall.pojo.entity.FruitEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppointmentCache extends AbstractCache {

    private static String Will_ON_SALE_KEY = "will_on_sale_key"; //预售

    private static final Integer TIMEOUT = 300;

    /**
     * 获取预售水果
     * @return
     */
    public List<AppointmentVO> getWillSaleFruitsFromCache(){
        return get(Will_ON_SALE_KEY, new TypeReference<List<AppointmentVO>>() {});
    }

    public void putWillSaleFruitsToCache(List<AppointmentVO> fruits) {
        put(Will_ON_SALE_KEY, fruits, TIMEOUT);
    }
}
