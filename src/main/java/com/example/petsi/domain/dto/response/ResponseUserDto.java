package com.example.petsi.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseUserDto {

    private Long id;
    private String email;
    private String password;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
