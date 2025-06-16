package com.example.petsi.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendCodeRequestDto {
    private String phoneNumber;
}