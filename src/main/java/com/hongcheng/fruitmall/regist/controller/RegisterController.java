package com.hongcheng.fruitmall.regist.controller;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.common.util.Response;
import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import com.hongcheng.fruitmall.regist.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fruitmall/user/v1")
public class RegisterController {

    @Autowired
    private RegisterService service;

    @PostMapping("/register")
    public RestResponse<Boolean> register(LoginEntity logInfo) {
        return RestResponse.success(service.register(logInfo));
    }

    @GetMapping("/active")
    public RestResponse<Boolean> active(MailUserInfo info) {
        return RestResponse.success(service.confirm(info.getEmail(),info.getCode()));
    }
}
