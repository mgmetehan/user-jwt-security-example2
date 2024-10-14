package com.mgmetehan.userjwtsecurityexample2.common.dto.response;

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
public class ResponseTokenDTO {
    private String accessToken;
    private Long accessTokenExpiresAt;
    private String refreshToken;
}
