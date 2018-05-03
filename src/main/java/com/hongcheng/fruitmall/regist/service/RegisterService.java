package com.hongcheng.fruitmall.regist.service;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.login.dao.mapper.LoginEntityMapper;
import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import com.hongcheng.fruitmall.regist.dao.cache.RegisterCache;
import com.hongcheng.fruitmall.ucenter.dao.mapper.UserEntityMapper;
import com.hongcheng.fruitmall.ucenter.pojo.entity.UserEntity;
import com.hongcheng.fruitmall.ucenter.enums.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private LoginEntityMapper loginMapper;

    @Autowired
    private UserEntityMapper userMapper;

    @Autowired
    private RegisterCache cache;

    /**
     * 注册
     * @param entity
     * @return
     */
    public Boolean register(LoginEntity entity) {
        entity.setRegistTime(LocalDateTime.now());
        //生成随机验证码并存入缓存
        Integer registerCode = (int)(Math.random()*1000000);
        cache.putRegisterCode(entity.getEmail(), registerCode);
        //密码加密存储
        entity.setPassword(MD5.getMD5(entity.getPassword()+entity.getRegistTime()));
        // 发送激活邮件,加入缓存队列
        loginMapper.insert(entity);
        MailUserInfo mailInfo = new MailUserInfo();
        mailInfo.setEmail(entity.getEmail());
        mailInfo.setCode(registerCode);
        mailInfo.setUserName(entity.getUserName());
        cache.pushMail(mailInfo);
        return true;
    }

    /**
     * 验证
     * @param email
     * @param code
     * @return
     */
    public Boolean confirm(String email, Integer code) {
        boolean equals = Optional.of(code)
                .equals(cache.getRegisterCode(email));
        //验证成功就创建一个用户
        if(equals) {
            //生成user
            UserEntity user = new UserEntity(email);
            userMapper.insert(user);
            //更新login
            LoginEntity info = new LoginEntity();
            info.setUserId(user.getUserId());
            info.setState(UserState.ACTIVE.getValue());
            loginMapper.update(info);
            cache.clean(email);
        }
        return equals;
    }
}
