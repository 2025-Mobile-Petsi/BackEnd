package com.example.petsi.domain.controller;

import com.example.petsi.domain.dto.request.CreateCommentRequestDto;
import com.example.petsi.domain.dto.request.UpdateCommentRequestDto;
import com.example.petsi.domain.dto.response.CommentResponseDto;
import com.example.petsi.domain.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> create(@Valid @RequestBody CreateCommentRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCommentRequestDto dto) {
        return ResponseEntity.ok(commentService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @GetMapping("/community/{comunityId}")
    public ResponseEntity<List<CommentResponseDto>> getByCommunity(@PathVariable Long comunityId) {
        return ResponseEntity.ok(commentService.getCommentsByCommunity(comunityId));
    }
}