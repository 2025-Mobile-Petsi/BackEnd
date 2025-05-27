package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.LoginRequestDto;
import com.example.petsi.domain.dto.request.RequestUserDto;
import com.example.petsi.domain.dto.response.ResponseUserDto;
import com.example.petsi.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseUserDto> signUp(@Valid @RequestBody RequestUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUserDto> login(@Valid @RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseUserDto> email(@PathVariable String email) {
        ResponseUserDto resp = userService.getUserByEmail(email);
        return ResponseEntity.ok(resp);
    }
}
