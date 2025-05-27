package com.example.petsi.domain.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUserDto {
    @Email @NotBlank
    String email;

    @Size(min = 8, max= 30)
    String password;

    @NotBlank
    String username;
}
