package com.example.petsi.domain.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EndWalkLogRequestDto {
    private LocalDateTime endTime;
    private double distance;
    private String weather;
}
