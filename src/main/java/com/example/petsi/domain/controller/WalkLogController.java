package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.CreateWalkLogRequestDto;
import com.example.petsi.domain.dto.response.WalkLogResponseDto;
import com.example.petsi.domain.service.WalkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class WalkLogController {

    private final WalkLogService walkLogService;

    @PostMapping
    public ResponseEntity<WalkLogResponseDto> create(@RequestBody CreateWalkLogRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(walkLogService.createLog(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WalkLogResponseDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(walkLogService.getLogsByUser((userId)));
    }
}
