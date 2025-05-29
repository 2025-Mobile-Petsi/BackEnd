package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.CreateCommunityRequestDto;
import com.example.petsi.domain.dto.request.UpdateCommunityRequestDto;
import com.example.petsi.domain.dto.response.CommunityResponseDto;
import com.example.petsi.domain.service.CommunityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @PostMapping
    public ResponseEntity<CommunityResponseDto> create(@Valid @RequestBody CreateCommunityRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.createPost(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommunityResponseDto> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(communityService.getPost(id));
    }

    @GetMapping
    public ResponseEntity<List<CommunityResponseDto>> getAllPosts() {
        return ResponseEntity.ok(communityService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunityResponseDto> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommunityRequestDto dto) {
        return ResponseEntity.ok(communityService.updatePost(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        communityService.deletePost(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}