package com.example.petsi.domain.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class WalkLogResponseDto {
    private Long id;
    private String username;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDate today;
    private double distance;
    private String weather;
}
