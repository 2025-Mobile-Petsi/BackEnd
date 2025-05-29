package com.example.petsi.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCommentRequestDto {

    @NotBlank
    private String content;

    @NotNull
    private Long userId;

    @NotNull
    private Long communityId;



}
