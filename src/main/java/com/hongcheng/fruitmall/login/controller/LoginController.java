package com.hongcheng.fruitmall.login.controller;

import com.hongcheng.fruitmall.common.constants.RestResponse;
import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import com.hongcheng.fruitmall.login.request.PassEditRequest;
import com.hongcheng.fruitmall.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/fruitmall/user/v1")
public class LoginController {

    @Autowired
    LoginService service;

    @PostMapping("/login")
    public RestResponse<Boolean> login(@RequestBody LoginEntity login, HttpSession session) {
        return RestResponse.success(service.login(login,session));
    }

    @PostMapping("/login/forgetPass")
    public RestResponse<Boolean> forgetPass(@RequestBody LoginEntity login) {
        return RestResponse.success(service.forgetPass(login));
    }

    @PostMapping("/login/updatePass")
    public RestResponse<Boolean> updatePass(@RequestBody PassEditRequest request) {
        return RestResponse.success(service.updatePass(request));
    }
}
