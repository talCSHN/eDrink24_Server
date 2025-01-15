package org.eDrink24.config.redisConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisHost, redisPort);
    }
    // sms인증 템플릿
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    // store 정보 템플릿
    @Bean
    public RedisTemplate<String, Object> redisStoreTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisStoreTemplate = new RedisTemplate<>();
        redisStoreTemplate.setConnectionFactory(redisConnectionFactory);
        // String Type 저장
        redisStoreTemplate.setKeySerializer(new StringRedisSerializer());
        // JSON 형태로 저장
        redisStoreTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisStoreTemplate;
    }
}
