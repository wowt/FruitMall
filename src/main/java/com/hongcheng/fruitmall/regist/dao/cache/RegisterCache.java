package com.hongcheng.fruitmall.regist.dao.cache;

import com.hongcheng.fruitmall.common.cache.AbstractCache;
import com.hongcheng.fruitmall.login.pojo.vo.MailUserInfo;
import org.springframework.stereotype.Component;

@Component
public class RegisterCache extends AbstractCache {

    private static final String REGISTER_KEY = "register_key_";

    private static final String MAIL_ACTIVE_QUEUE_KEY="mail_active_queue_key";

    private static final Integer TIMEOUT = 24*60*60; //一天后过期

    /**
     * 缓存激活码
     * @param email
     * @param code
     */
    public void putRegisterCode(String email,Integer code) {
        put(REGISTER_KEY+email,code,TIMEOUT);
    }

    public Integer getRegisterCode(String email) {
        return get(REGISTER_KEY+email,Integer.class);
    }

    public void clean(String email) {
        delete(REGISTER_KEY+email);
    }

    public void pushMail(MailUserInfo info) {
        pushFromTail(MAIL_ACTIVE_QUEUE_KEY,info);
    }
}
