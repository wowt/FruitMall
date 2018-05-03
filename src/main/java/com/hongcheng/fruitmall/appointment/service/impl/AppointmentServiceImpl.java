package com.hongcheng.fruitmall.appointment.service.impl;

import com.hongcheng.fruitmall.appointment.dao.mapper.AppointmentMapper;
import com.hongcheng.fruitmall.appointment.pojo.entity.WillSaleEntity;
import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import com.hongcheng.fruitmall.appointment.service.AppointmentService;
import com.hongcheng.fruitmall.common.pojo.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper mapper;

    @Override
    public Integer addSubscribe(Integer userId,Integer productId) {
        return mapper.addSub(userId, productId);
    }

    @Override
    public PageList<AppointmentVO> getList(Integer userId) {
        List<AppointmentVO> list = mapper.getList();
        list.forEach(vo -> {
            //是否已经订阅过了
            if(vo != null) {
                vo.setHasSub(Optional.ofNullable(mapper.getByProductIdAndUserId(userId, vo.getProductId()))
                        .isPresent());
            }
        });
        return null;
    }
}



