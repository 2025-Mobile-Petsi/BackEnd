package com.example.petsi.domain.dto.request;

import com.example.petsi.domain.type.CommunityCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCommunityRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private CommunityCategory category;
}