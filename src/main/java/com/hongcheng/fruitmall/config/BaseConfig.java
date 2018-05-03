package com.hongcheng.fruitmall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAsync
public class BaseConfig {

    @Bean(name = "redisWriteTemplate")
    public StringRedisTemplate redisWriteTemplate(
            @Value("${redis.write.host}") String hostName,
            @Value("${redis.write.port}") int port,
            @Value("${redis.write.maxidleclient}") int maxIdle,
            @Value("${redis.write.maxtotalclient}") int maxTotal,
            @Value("${redis.write.maxwait}") long maxWaitMillis) {

        return getSimpleTemplate(hostName, port, maxIdle, maxTotal, maxWaitMillis);
    }

    @Bean(name = "redisReadTemplate")
    public StringRedisTemplate redisReadTemplate(
            @Value("${redis.read.host}") String hostName,
            @Value("${redis.read.port}") int port,
            @Value("${redis.read.maxidleclient}") int maxIdle,
            @Value("${redis.read.maxtotalclient}") int maxTotal,
            @Value("${redis.read.maxwait}") long maxWaitMillis) {

        return getSimpleTemplate(hostName, port, maxIdle, maxTotal, maxWaitMillis);
    }

    private StringRedisTemplate getSimpleTemplate(String hostName, int port, int maxIdle, int maxTotal, long maxWaitMillis) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory(hostName, port, "", maxIdle, maxTotal, 0, maxWaitMillis, true));
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }

    private RedisConnectionFactory connectionFactory(String hostName, int port,
                                                     String password, int maxIdle, int maxTotal, int index,
                                                     long maxWaitMillis, boolean testOnBorrow) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(hostName);
        jedis.setPort(port);
        if (!StringUtils.isEmpty(password)) {
            jedis.setPassword(password);
        }
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setPoolConfig(poolConfig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        // 初始化连接pool
        jedis.afterPropertiesSet();
        return jedis;
    }

    private JedisPoolConfig poolConfig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(100);
        return executor;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }
}
