package com.hongcheng.fruitmall.appointment.controller;

import com.hongcheng.fruitmall.appointment.pojo.vo.AppointmentVO;
import com.hongcheng.fruitmall.appointment.service.AppointmentService;
import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.pojo.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fruit-mall/appointment/v1")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @GetMapping("/list")
    public RestResponse<PageList<AppointmentVO>> getList(@SessionAttribute(value = "userId",required = false) Integer userId) {
        return RestResponse.success(service.getList(userId));
    }

    @PostMapping("/sub/{id}")
    public RestResponse<Integer> addSub(@SessionAttribute("userId")Integer userId,@PathVariable Integer id){
        return RestResponse.success(service.addSubscribe(userId,id));
    }

}
