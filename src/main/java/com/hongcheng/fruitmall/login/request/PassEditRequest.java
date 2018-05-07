package com.hongcheng.fruitmall.login.request;

import lombok.Data;

@Data
public class PassEditRequest {

    private String email;

    private String oldPassword;

    private String password;

    private Integer code;
}
