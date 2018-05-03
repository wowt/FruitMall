package com.hongcheng.fruitmall.appointment.dao.mapper;

import com.hongcheng.fruitmall.appointment.pojo.entity.WillSaleEntity;
import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppointmentMapper {

    /**
     * 加入订阅
     * @param userId
     * @return
     */
    int addSub(@Param("userId") Integer userId,@Param("productId") Integer productId);

    /**
     * 获取预售列表
     * @return
     */
    List<AppointmentVO> getList();

    /**
     * 查看是否已经订阅过了
     * @param userId
     * @param productId
     * @return
     */
    WillSaleEntity getByProductIdAndUserId(@Param("userId") Integer userId,@Param("productId") Integer productId);


}
