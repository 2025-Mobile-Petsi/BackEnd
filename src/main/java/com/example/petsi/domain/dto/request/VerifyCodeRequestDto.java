package com.example.petsi.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyCodeRequestDto {
    private String phoneNumber;
    private String code;
}