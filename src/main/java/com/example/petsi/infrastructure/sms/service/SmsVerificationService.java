package com.example.petsi.infrastructure.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsVerificationService {

    private final RedisTemplate<String, String> redisTemplate;

    public boolean isVerified(String phoneNumber) {
        return redisTemplate.hasKey("VERIFIED:" + phoneNumber);
    }

    public void removeCode(String phoneNumber) {
        redisTemplate.delete("VERIFIED:" + phoneNumber);
    }
}