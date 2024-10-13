package com.mgmetehan.userjwtsecurityexample2.user.controller;


import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.LoginRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.TokenRefreshRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.UserRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseTokenDTO;
import com.mgmetehan.userjwtsecurityexample2.user.service.UserLoginService;
import com.mgmetehan.userjwtsecurityexample2.user.service.UserRefreshTokenService;
import com.mgmetehan.userjwtsecurityexample2.user.service.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;
    private final UserRefreshTokenService userRefreshTokenService;

    @PostMapping("/register")
    public CustomResponse<Void> registerUser(@RequestBody @Valid final UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegisterService.registerUser(userRegisterRequestDTO);
        return CustomResponse.SUCCESS;
    }

    @PostMapping("/login")
    public CustomResponse<ResponseTokenDTO> loginUser(@RequestBody @Valid final LoginRequestDTO loginRequestDTO) {
        final ResponseTokenDTO responseTokenDTO = userLoginService.login(loginRequestDTO);
        return CustomResponse.successOf(responseTokenDTO);
    }

    @PostMapping("/refresh-token")
    public CustomResponse<ResponseTokenDTO> refreshToken(@RequestBody @Valid final TokenRefreshRequestDTO tokenRefreshRequestDTO) {
        final ResponseTokenDTO responseTokenDTO = userRefreshTokenService.refreshToken(tokenRefreshRequestDTO);
        return CustomResponse.successOf(responseTokenDTO);
    }
}
