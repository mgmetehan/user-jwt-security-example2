package com.mgmetehan.userjwtsecurityexample2.user.service;

import com.mgmetehan.userjwtsecurityexample2.auth.service.TokenService;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import com.mgmetehan.userjwtsecurityexample2.common.converter.TokenResponseConverter;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.LoginRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.response.ResponseTokenDTO;
import com.mgmetehan.userjwtsecurityexample2.user.model.User;
import com.mgmetehan.userjwtsecurityexample2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseTokenDTO login(LoginRequestDTO loginRequestDTO) {
        final User userFromDB = userRepository
                .findUserByEmail(loginRequestDTO.getEmail())
                .orElseThrow(
                        () -> new GeneralException(ErrorStatusCode.USER_NOT_FOUND,
                                loginRequestDTO.getEmail()));

        if (Boolean.FALSE.equals(passwordEncoder.matches(
                loginRequestDTO.getPassword(), userFromDB.getPassword()))) {
            throw new GeneralException(ErrorStatusCode.PASSWORD_NOT_VALID);
        }
        return TokenResponseConverter.convert(tokenService.generateToken(userFromDB.getClaims()));
    }
}
