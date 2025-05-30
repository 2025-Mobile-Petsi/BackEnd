package com.example.petsi.domain.dto.request;

import lombok.Getter;

@Getter
public class CreateDiaryRequestDto {

    private String title;
    private String content;
    private String imageUrl;
    private String weather;
    private Long userId;
    private Long walkLogId;
}
