package com.example.petsi.domain.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWalkLogRequestDto {

    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double distance;
    private String weather;
}
