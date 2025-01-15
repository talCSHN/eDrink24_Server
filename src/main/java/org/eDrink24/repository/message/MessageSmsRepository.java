package org.eDrink24.repository.message;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class MessageSmsRepository {
    private final int LIMIT_TIME = 3*60;

    public final StringRedisTemplate stringRedisTemplate;
    public MessageSmsRepository(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void createSmsCertification(String phoneNum, String certificateCode) {
        stringRedisTemplate.opsForValue()
                .set(phoneNum, certificateCode, Duration.ofSeconds(LIMIT_TIME));
    }

    public String getSmsCertification(String phoneNum) {
        return stringRedisTemplate.opsForValue().get(phoneNum);
    }

    public void removeSmsCertification(String phoneNum) {
        stringRedisTemplate.delete(phoneNum);
    }
}
