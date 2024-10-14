package com.mgmetehan.userjwtsecurityexample2.common.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenInvalidateRequestDTO {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;
}
