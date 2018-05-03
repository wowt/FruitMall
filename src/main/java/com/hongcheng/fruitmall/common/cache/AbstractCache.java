package com.hongcheng.fruitmall.common.cache;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AbstractCache {

    @Resource(name ="redisWriteTemplate")
    private StringRedisTemplate redisWriteTemplate;

    @Resource(name = "redisReadTemplate")
    private StringRedisTemplate redisReadTemplate;

    @Resource
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(AbstractCache.class);

    protected  <T> T get(String key, Class<T> classType) {
        String jsonValue = redisReadTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(jsonValue)){
            return null;
        }
        try {
            return objectMapper.readValue(jsonValue, classType);
        } catch (IOException e) {
            log.error("cache get error....",e.getMessage());
        }
        return null;
    }

    protected  <T> T get(String key, TypeReference<?> toValueTypeRef) {
        String jsonValue = redisReadTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(jsonValue)){
            return null;
        }
        try {
            return objectMapper.readValue(jsonValue, toValueTypeRef);
        } catch (IOException e) {
            log.error("cache get error....",e.getMessage());
        }
        return null;
    }

    protected void put(String key, Object o, long timeout) {
        try {
            redisWriteTemplate.opsForValue().set(key, objectMapper.writeValueAsString(o), timeout, TimeUnit.SECONDS);
        } catch (JsonProcessingException e) {
            log.error("cache set error....", e.getMessage());
        }
    }

    protected void delete(String key) {
        redisWriteTemplate.delete(key);
    }

    protected void deleteBatch(List<String> keys) {
        redisWriteTemplate.delete(keys);
    }

    protected  <T> T hget(String key, String field, Class<T> classType) {
        Object value = redisReadTemplate.opsForHash().get(key, field);
        if(value != null) {
            try {
                return objectMapper.readValue(value.toString(), classType);
            } catch (IOException e) {
                log.error("cache hget error...", e.getMessage());
            }
        }
        return null;
    }

    protected void hdel(String key, String filed) {
        redisWriteTemplate.opsForHash().delete(key, filed);
    }

    protected void hset(String key, String field, Object o, long timeout) {
        redisWriteTemplate.opsForHash().put(key, field, o);
        redisWriteTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * redis消息队列
     * @param key
     * @param value
     */
    protected void pushFromTail(String key,Object value) {
        String str = null;
        try {
            str = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("cache push to list failed......",e.getMessage());
        }
        redisWriteTemplate.boundListOps(key).rightPush(str);
    }

    protected  <T> T popFromHead(String key, Class<T> classTyp) {
        String value = redisReadTemplate.boundListOps(key).leftPop();
        if(!StringUtils.isEmpty(value)){
            try {
                return objectMapper.readValue(value, classTyp);
            } catch (IOException e) {
                log.error("pop failed....",e.getMessage());
            }
        }
        return null;
    }

    protected boolean exists(String key){
        return redisReadTemplate.hasKey(key);
    }

    protected void expire(String key, long timeout){
        redisWriteTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    protected void incr(String key){
        redisWriteTemplate.opsForValue().increment(key, 1);
    }

    protected void incrBy(String key, long increment){
        redisWriteTemplate.opsForValue().increment(key, increment);
    }

}
