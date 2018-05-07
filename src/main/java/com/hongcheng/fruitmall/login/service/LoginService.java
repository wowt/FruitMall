package com.hongcheng.fruitmall.login.service;

import com.hongcheng.fruitmall.common.codec.MD5;
import com.hongcheng.fruitmall.common.exception.BusinessException;
import com.hongcheng.fruitmall.login.dao.cache.LoginCache;
import com.hongcheng.fruitmall.login.dao.mapper.LoginEntityMapper;
import com.hongcheng.fruitmall.login.pojo.entity.LoginEntity;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import com.hongcheng.fruitmall.login.request.PassEditRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginEntityMapper loginMapper;

    @Autowired
    private LoginCache cache;

    /**
     * 登录
     * @param login
     * @param session
     * @return
     */
    public Boolean login(LoginEntity login, HttpSession session) {
        //Subject subject = SecurityUtils.getSubject();
        //UsernamePasswordToken token = new UsernamePasswordToken(login.getEmail(), login.getPassword());
        LoginEntity entity = loginMapper.getByEmail(login.getEmail());

        boolean verify = Optional.of(entity)
                .map(LoginEntity::getPassword)
                .equals(Optional.of(createPass(login.getPassword(),entity.getRegistTime().toLocalDate())));
        //存入session
        if (verify) {
            session.setAttribute("userId",entity.getUserId());
        }
        return verify;
    }

    /**
     * 忘记密码
     * @param login
     * @return
     */
    public Boolean forgetPass(LoginEntity login) {
        //查看是否已经申请过了
        MailUserInfo info = cache.getInfo(login.getEmail());
        if(info != null) {
            throw new BusinessException("你已经申请过了哦！");
        }
        //生成验证码
        Integer code = (int)(Math.random()*1000000);
        MailUserInfo mailUserInfo = new MailUserInfo();
        mailUserInfo.setCode(code);
        mailUserInfo.setEmail(login.getEmail());
        cache.addQueue(mailUserInfo);
        return true;
    }

    /**
     * 修改密码
     * @param request
     * @return
     */
    public Boolean updatePass(PassEditRequest request) {
        Boolean result = false;
        //验证
        MailUserInfo info = cache.getInfo(request.getEmail());
        if (info == null) {
            throw new BusinessException("修改失败，请重新申请");
        }
        //核对code
        boolean equals = info.getCode().equals(request.getCode());
        if (equals) {
            LoginEntity entity = loginMapper.getByEmail(request.getEmail());
            String pass = createPass(request.getPassword(), entity.getRegistTime().toLocalDate());
            entity.setPassword(pass);
            loginMapper.update(entity);
            cache.cleanPass(request.getEmail());
            result = true;
        }
        return result;
    }

    /**
     * 密码加密生成算法
     * @param password
     * @param registerTime
     * @return
     */
    private String createPass(String password, LocalDate registerTime) {
        return MD5.getMD5(password + registerTime.toString());
    }

    public Boolean changePass(Integer userId,PassEditRequest request) {
        LoginEntity entity = loginMapper.getByUserId(userId);
        boolean verify = Optional.of(entity)
                .map(LoginEntity::getPassword)
                .equals(Optional.of(createPass(request.getOldPassword(),entity.getRegistTime().toLocalDate())));

        if (verify) {
            String pass = createPass(request.getPassword(), entity.getRegistTime().toLocalDate());
            entity.setPassword(pass);
            loginMapper.update(entity);
            return true;
        }
        return false;
    }
}
