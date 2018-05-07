package com.hongcheng.fruitmall.appointment.service.impl;

import com.hongcheng.fruitmall.appointment.dao.cache.AppointmentCache;
import com.hongcheng.fruitmall.appointment.dao.mapper.AppointmentMapper;
import com.hongcheng.fruitmall.appointment.pojo.entity.WillSaleEntity;
import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import com.hongcheng.fruitmall.appointment.service.AppointmentService;
import com.hongcheng.fruitmall.common.exception.BusinessException;
import com.hongcheng.fruitmall.common.pojo.PageList;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private AppointmentCache cache;

    @Override
    public Integer addSubscribe(Integer userId,Integer productId) {
        if(userId == null) {
            throw new BusinessException("还没有登录哦！");
        }
        return mapper.addSub(userId, productId);
    }

    @Override
    public PageList<AppointmentVO> getList(Integer userId) {
        List<AppointmentVO> list = cache.getWillSaleFruitsFromCache();
        if(CollectionUtils.isEmpty(list)) {
            list = mapper.getList();
            cache.putWillSaleFruitsToCache(list);
        }
        if (userId != null) {
            list.forEach(vo -> {
                //是否已经订阅过了
                if(vo != null) {
                    vo.setHasSub(Optional.ofNullable(mapper.getByProductIdAndUserId(userId, vo.getProductId()))
                            .isPresent());
                }
            });
        }

        return new PageList<>(list.size(),list);
    }
}



