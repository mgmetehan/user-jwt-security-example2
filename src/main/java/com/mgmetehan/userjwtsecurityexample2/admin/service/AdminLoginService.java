package com.mgmetehan.userjwtsecurityexample2.admin.service;


import com.mgmetehan.userjwtsecurityexample2.admin.model.Admin;
import com.mgmetehan.userjwtsecurityexample2.admin.repository.AdminRepository;
import com.mgmetehan.userjwtsecurityexample2.auth.service.TokenService;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import com.mgmetehan.userjwtsecurityexample2.common.converter.TokenResponseConverter;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.LoginRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.response.ResponseTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminLoginService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseTokenDTO login(LoginRequestDTO loginRequestDTO) {
        final Admin adminFromDB = adminRepository
                .findAdminByEmail(loginRequestDTO.getEmail())
                .orElseThrow(
                        () -> new GeneralException(ErrorStatusCode.ADMIN_NOT_FOUND,
                                loginRequestDTO.getEmail()));

        if (Boolean.FALSE.equals(passwordEncoder
                .matches(loginRequestDTO.getPassword(), adminFromDB.getPassword()))) {
            throw new GeneralException(ErrorStatusCode.PASSWORD_NOT_VALID);
        }
        return TokenResponseConverter.convert(tokenService.generateToken(adminFromDB.getClaims()));
    }
}
