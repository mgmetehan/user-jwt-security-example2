package com.mgmetehan.userjwtsecurityexample2.admin.service;

import com.mgmetehan.userjwtsecurityexample2.auth.service.InvalidTokenService;
import com.mgmetehan.userjwtsecurityexample2.auth.service.TokenService;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.TokenInvalidateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminLogoutService {
    private final TokenService tokenService;
    private final InvalidTokenService invalidTokenService;

    public void logout(TokenInvalidateRequestDTO tokenInvalidateRequestDTO) {

        tokenService.verifyAndValidate(
                Set.of(
                        tokenInvalidateRequestDTO.getAccessToken(),
                        tokenInvalidateRequestDTO.getRefreshToken()
                )
        );

        final String accessTokenId = tokenService
                .getPayload(tokenInvalidateRequestDTO.getAccessToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(accessTokenId);

        final String refreshTokenId = tokenService
                .getPayload(tokenInvalidateRequestDTO.getRefreshToken())
                .getId();

        invalidTokenService.checkForInvalidityOfToken(refreshTokenId);

        invalidTokenService.invalidateTokens(Set.of(accessTokenId, refreshTokenId));
    }
}
