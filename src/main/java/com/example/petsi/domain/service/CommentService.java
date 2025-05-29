package com.example.petsi.domain.service;

import com.example.petsi.domain.dto.request.CreateCommentRequestDto;
import com.example.petsi.domain.dto.request.UpdateCommentRequestDto;
import com.example.petsi.domain.dto.response.CommentResponseDto;
import com.example.petsi.domain.entity.Comment;
import com.example.petsi.domain.entity.Community;
import com.example.petsi.domain.entity.User;
import com.example.petsi.domain.repository.CommentRepository;
import com.example.petsi.domain.repository.CommunityRepository;
import com.example.petsi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    public CommentResponseDto create(CreateCommentRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));
        Community community = communityRepository.findById(dto.getCommunityId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다."));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .user(user)
                .community(community)
                .build();

        return toResponse(commentRepository.save(comment));
    }

    public CommentResponseDto update(Long commentId, UpdateCommentRequestDto dto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));

        comment.updateContent(dto.getContent());
        return toResponse(commentRepository.save(comment));
    }

    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        commentRepository.delete(comment);
    }

    public List<CommentResponseDto> getCommentsByCommunity(Long communityId) {
        return commentRepository.findByCommunityId(communityId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CommentResponseDto getCommentById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글이 존재하지 않습니다."));
        return toResponse(comment);
    }


    private CommentResponseDto toResponse(Comment c) {
        return CommentResponseDto.builder()
                .id(c.getId())
                .content(c.getContent())
                .username(c.getUser().getUsername())
                .communityId(c.getCommunity().getId())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}