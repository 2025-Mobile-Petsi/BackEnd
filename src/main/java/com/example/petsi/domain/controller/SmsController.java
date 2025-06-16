package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.SendCodeRequestDto;
import com.example.petsi.domain.dto.request.VerifyCodeRequestDto;
import com.example.petsi.infrastructure.sms.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
@RequiredArgsConstructor
public class SmsController {

    private final AuthService authService;

    @PostMapping("/send-code")
    public ResponseEntity<Void> sendCode(@RequestBody SendCodeRequestDto request) {
        authService.sendVerificationCode(request.getPhoneNumber());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-code")
    public ResponseEntity<Void> verifyCode(
            @RequestBody VerifyCodeRequestDto req) {
        boolean verified = authService.verifyCode(
                req.getPhoneNumber(), req.getCode());
        return verified
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }
}