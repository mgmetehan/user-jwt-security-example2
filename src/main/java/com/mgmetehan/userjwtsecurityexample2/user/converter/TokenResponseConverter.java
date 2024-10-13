package com.mgmetehan.userjwtsecurityexample2.user.converter;

import com.mgmetehan.userjwtsecurityexample2.auth.model.Token;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseTokenDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenResponseConverter {

    public ResponseTokenDTO convert(Token token) {
        return ResponseTokenDTO.builder()
                .accessToken(token.getAccessToken())
                .accessTokenExpiresAt(token.getAccessTokenExpiresAt())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
