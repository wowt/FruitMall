package com.hongcheng.fruitmall.login.dao.cache;

import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import org.springframework.stereotype.Component;

@Component
public class LoginCache extends AbstractCache {

    private static String MAIL_FORGETPASS_QUEUE_KEY="mail_forgetpass_queue_key";//忘记密码队列

    private static String LOGIN_FORGET_PASS_KEY = "login_Pass_key_";

    private static Integer timeOut = 24*60*60;


    /**
     * 加入发送邮件缓存队列
     * @param info
     */
    public void addQueue(MailUserInfo info) {
        put(LOGIN_FORGET_PASS_KEY+info.getEmail(),info,timeOut);
        pushFromTail(MAIL_FORGETPASS_QUEUE_KEY,info);
    }

    public MailUserInfo getInfo(String email) {
        return get(LOGIN_FORGET_PASS_KEY + email, MailUserInfo.class);
    }
}
