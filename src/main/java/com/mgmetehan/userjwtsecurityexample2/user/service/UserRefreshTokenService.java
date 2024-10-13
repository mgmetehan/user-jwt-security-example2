package com.mgmetehan.userjwtsecurityexample2.user.service;

import com.mgmetehan.userjwtsecurityexample2.auth.model.type.TokenClaims;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.UserStatus;
import com.mgmetehan.userjwtsecurityexample2.auth.service.TokenService;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import com.mgmetehan.userjwtsecurityexample2.user.converter.TokenResponseConverter;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.TokenRefreshRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseTokenDTO;
import com.mgmetehan.userjwtsecurityexample2.user.model.User;
import com.mgmetehan.userjwtsecurityexample2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRefreshTokenService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public ResponseTokenDTO refreshToken(TokenRefreshRequestDTO tokenRefreshRequest) {
        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String userId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final User userFromDB = userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new GeneralException(ErrorStatusCode.NOT_FOUND));

        validateUserStatus(userFromDB);

        return TokenResponseConverter.convert(tokenService.generateToken(
                userFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        ));
    }

    private void validateUserStatus(final User user) {
        if (!(UserStatus.ACTIVE.equals(user.getUserStatus()))) {
            throw new GeneralException(ErrorStatusCode.USER_STATUS_NOT_VALID,
                    String.valueOf(user.getUserStatus()));
        }
    }
}
