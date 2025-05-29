package com.example.petsi.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;
    private String username;
    private Long communityId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
