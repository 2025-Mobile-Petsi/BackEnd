package com.example.petsi.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DiaryResponseDto {

    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String weather;
    private String username;
}
