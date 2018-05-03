package com.hongcheng.fruitmall.appointment.service;

import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import com.hongcheng.fruitmall.common.pojo.PageList;

public interface AppointmentService {

    /**
     * 加入发布提醒
     * @param userId
     * @return
     */
    Integer addSubscribe(Integer userId,Integer productId);

    /**
     * 获取预售列表
     * @param userId
     * @return
     */
    PageList<AppointmentVO> getList(Integer userId);


}
