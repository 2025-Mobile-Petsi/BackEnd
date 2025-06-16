package com.example.petsi.infrastructure.sms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RedisTemplate<String, String> redisTemplate;
    private final SmsUtil smsUtil;

    private static final Duration CODE_EXPIRATION = Duration.ofMinutes(3);

    public void sendVerificationCode(String phoneNumber) {
        String code = generateRandomCode();
        redisTemplate.opsForValue()
                .set(codeKey(phoneNumber), code, CODE_EXPIRATION);
        smsUtil.sendVerificationCode(phoneNumber, code);
    }

    public boolean verifyCode(String phoneNumber, String code) {
        String stored = redisTemplate.opsForValue().get(codeKey(phoneNumber));

        if (code.equals(stored)) {
            redisTemplate.delete(codeKey(phoneNumber));
            redisTemplate.opsForValue()
                    .set(verifiedKey(phoneNumber), "true", CODE_EXPIRATION);
            return true;
        }
        return false;
    }

    private String generateRandomCode() {
        return String.valueOf(ThreadLocalRandom.current()
                .nextInt(100_000, 1_000_000));
    }

    private String codeKey(String phone)     { return "CODE:"     + phone; }
    private String verifiedKey(String phone) { return "VERIFIED:" + phone; }
}