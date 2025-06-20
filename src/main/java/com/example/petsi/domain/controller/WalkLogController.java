package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.EndWalkLogRequestDto;
import com.example.petsi.domain.dto.request.StartWalkLogRequestDto;
import com.example.petsi.domain.dto.response.WalkLogResponseDto;
import com.example.petsi.domain.service.WalkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/walk-logs")
@RequiredArgsConstructor
public class WalkLogController {

    private final WalkLogService walkLogService;

    @PostMapping("/start")
    public ResponseEntity<WalkLogResponseDto> startWalk(@RequestBody StartWalkLogRequestDto dto) {
        return ResponseEntity.ok(walkLogService.startWalk(dto.getUserId()));
    }

    @PutMapping("/end/{walkLogId}")
    public ResponseEntity<WalkLogResponseDto> endWalk(@PathVariable Long walkLogId, @RequestBody EndWalkLogRequestDto dto) {
        return ResponseEntity.ok(walkLogService.endWalk(walkLogId, dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WalkLogResponseDto>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(walkLogService.getLogsByUser((userId)));
    }
}
