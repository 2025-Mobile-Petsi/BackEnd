package com.example.petsi.domain.service;

import com.example.petsi.domain.dto.request.LoginRequestDto;
import com.example.petsi.domain.dto.request.SignUpRequestUserDto;
import com.example.petsi.domain.dto.response.ResponseUserDto;
import com.example.petsi.domain.entity.User;
import com.example.petsi.domain.repository.UserRepository;
import com.example.petsi.infrastructure.sms.service.SmsVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final SmsVerificationService smsVerificationService;

    public ResponseUserDto signUp(SignUpRequestUserDto dto) {

        if (!smsVerificationService.isVerified(dto.getPhoneNumber())) {
            throw new IllegalArgumentException("전화번호 인증이 완료되지 않았습니다.");
        }

        repository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new IllegalArgumentException("이미 존재하는 이메일"); });

        User saved = repository.save(User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))
                .username(dto.getUsername())
                .phoneNumber(dto.getPhoneNumber())
                .build());

        smsVerificationService.removeCode(dto.getPhoneNumber()); // 인증 플래그 제거

        return toResponse(saved);
    }

    public ResponseUserDto login(LoginRequestDto dto) {

        User user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("이메일/비밀번호 확인"));

        if (!encoder.matches(dto.getPassword(), user.getPassword()))
            throw new BadCredentialsException("이메일/비밀번호 확인");

        return toResponse(user);
    }

    public ResponseUserDto getUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원"));
        return toResponse(user);
    }

    public ResponseUserDto getUserByEmail(String email) {
        User user = repository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원"));
        return toResponse(user);
    }

    public boolean isEmailDuplicate(String email) {
        return repository.findByEmail(email).isPresent();
    }

    private ResponseUserDto toResponse(User u) {
        return ResponseUserDto.builder()
                .id(u.getId())
                .email(u.getEmail())
                .username(u.getUsername())
                .phoneNumber(u.getPhoneNumber())
                .createdAt(u.getCreatedAt())
                .updatedAt(u.getUpdatedAt())
                .build();
    }
}

