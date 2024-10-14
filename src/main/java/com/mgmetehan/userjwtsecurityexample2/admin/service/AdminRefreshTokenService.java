package com.mgmetehan.userjwtsecurityexample2.admin.service;

import com.mgmetehan.userjwtsecurityexample2.admin.model.Admin;
import com.mgmetehan.userjwtsecurityexample2.admin.repository.AdminRepository;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.TokenClaims;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.UserStatus;
import com.mgmetehan.userjwtsecurityexample2.auth.service.TokenService;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import com.mgmetehan.userjwtsecurityexample2.common.converter.TokenResponseConverter;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.TokenRefreshRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.response.ResponseTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminRefreshTokenService {
    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    public ResponseTokenDTO refreshToken(TokenRefreshRequestDTO tokenRefreshRequestDTO) {

        tokenService.verifyAndValidate(tokenRefreshRequestDTO.getRefreshToken());

        final String adminId = tokenService
                .getPayload(tokenRefreshRequestDTO.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final Admin adminFromDB = adminRepository
                .findById(adminId)
                .orElseThrow(
                        () -> new GeneralException(ErrorStatusCode.NOT_FOUND));

        this.validateAdminStatus(adminFromDB);

        return TokenResponseConverter.convert(tokenService.generateToken(
                adminFromDB.getClaims(),
                tokenRefreshRequestDTO.getRefreshToken()
        ));
    }

    private void validateAdminStatus(final Admin admin) {
        if (!(UserStatus.ACTIVE.equals(admin.getUserStatus()))) {
            throw new GeneralException(ErrorStatusCode.ADMIN_STATUS_NOT_VALID,
                    String.valueOf(admin.getUserStatus()));
        }
    }
}
