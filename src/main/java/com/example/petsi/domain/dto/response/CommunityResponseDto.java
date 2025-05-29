package com.example.petsi.domain.dto.response;

import com.example.petsi.domain.type.CommunityCategory;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityResponseDto {

    private Long id;
    private String title;
    private String content;
    private CommunityCategory category;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}