package com.example.petsi.infrastructure.redis;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisHealthCheck {

    private final StringRedisTemplate redisTemplate;

    @PostConstruct
    public void checkConnection() {
        try {
            LettuceConnectionFactory f =
                    (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            log.info("🔍 Lettuce 연결 정보  host={}, port={}", f.getHostName(), f.getPort());
            log.info("📌 RedisTemplate 존재 여부: {}", redisTemplate != null);
            log.info("📌 ConnectionFactory: {}", redisTemplate.getConnectionFactory());

            redisTemplate.opsForValue().set("health_check", "pong");
            String result = redisTemplate.opsForValue().get("health_check");

            if ("pong".equals(result)) {
                log.info("✅ Redis 연결 성공: {}", result);
            } else {
                log.warn("⚠️ Redis 응답 이상: {}", result);
            }
        } catch (Exception e) {
            log.error("❌ Redis 연결 실패", e);
        }
    }
}