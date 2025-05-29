package com.example.petsi.domain.service;

import com.example.petsi.domain.dto.request.CreateCommunityRequestDto;
import com.example.petsi.domain.dto.request.UpdateCommunityRequestDto;
import com.example.petsi.domain.dto.response.CommunityResponseDto;
import com.example.petsi.domain.entity.Community;
import com.example.petsi.domain.entity.User;
import com.example.petsi.domain.repository.CommunityRepository;
import com.example.petsi.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public CommunityResponseDto createPost(CreateCommunityRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));

        Community post = Community.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(dto.getCategory())
                .user(user)
                .build();

        return toResponse(communityRepository.save(post));
    }

    public CommunityResponseDto getPost(Long id) {
        Community post = communityRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        return toResponse(post);
    }

    public List<CommunityResponseDto> getAllPosts() {
        return communityRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CommunityResponseDto updatePost(Long postId, UpdateCommunityRequestDto dto) {
        Community post = communityRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setCategory(dto.getCategory());

        return toResponse(communityRepository.save(post));
    }

    public void deletePost(Long postId) {
        Community post = communityRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("게시글이 존재하지 않습니다."));
        communityRepository.delete(post);
    }

    private CommunityResponseDto toResponse(Community c) {
        return CommunityResponseDto.builder()
                .id(c.getId())
                .title(c.getTitle())
                .content(c.getContent())
                .category(c.getCategory())
                .username(c.getUser().getUsername())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }

}
