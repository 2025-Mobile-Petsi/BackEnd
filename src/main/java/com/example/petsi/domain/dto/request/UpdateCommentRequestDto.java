package com.example.petsi.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateCommentRequestDto {

    @NotBlank
    private String content;
}
