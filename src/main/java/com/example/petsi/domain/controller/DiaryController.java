package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.CreateDiaryRequestDto;
import com.example.petsi.domain.dto.response.DiaryResponseDto;
import com.example.petsi.domain.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<DiaryResponseDto> create(@RequestBody CreateDiaryRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diaryService.create(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DiaryResponseDto>> getUserDiaries(@PathVariable Long userId) {
        return ResponseEntity.ok(diaryService.getByUserId(userId));
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryResponseDto> getDiary(@PathVariable Long diaryId) {
        return ResponseEntity.ok(diaryService.getById(diaryId));
    }

}
