package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.CheckEmailRequestDto;
import com.example.petsi.domain.dto.request.SignUpRequestUserDto;
import com.example.petsi.domain.dto.response.ResponseUserDto;
import com.example.petsi.domain.service.UserService;
import com.example.petsi.infrastructure.sms.service.SmsVerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final SmsVerificationService smsVerificationService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseUserDto> signUp(@Valid @RequestBody SignUpRequestUserDto dto) {
        if (!smsVerificationService.isVerified(dto.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(null); // 인증 안된 번호
        }
        ResponseUserDto user = userService.signUp(dto);
        smsVerificationService.removeCode(dto.getPhoneNumber());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestBody CheckEmailRequestDto request) {
        boolean isDuplicate = userService.isEmailDuplicate(request.getEmail());
        return ResponseEntity.ok(isDuplicate);
    }
}