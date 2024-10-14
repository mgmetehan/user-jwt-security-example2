package com.mgmetehan.userjwtsecurityexample2.admin.controller;

import com.mgmetehan.userjwtsecurityexample2.admin.dto.request.AdminRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminLoginService;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminRefreshTokenService;
import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminRegisterService;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.LoginRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.TokenRefreshRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseTokenDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/admin")
@RequiredArgsConstructor
public class AdminAuthController {
    private final AdminRegisterService adminRegisterService;
    private final AdminLoginService adminLoginService;
    private final AdminRefreshTokenService adminRefreshTokenService;

    @PostMapping("/register")
    public CustomResponse<Void> registerAdmin(@RequestBody @Valid final AdminRegisterRequestDTO adminRegisterRequestDTO) {
        adminRegisterService.registerAdmin(adminRegisterRequestDTO);
        return CustomResponse.SUCCESS;
    }

    @PostMapping("/login")
    public CustomResponse<ResponseTokenDTO> loginAdmin(@RequestBody @Valid final LoginRequestDTO loginRequestDTO) {
        final ResponseTokenDTO tokenResponse = adminLoginService.login(loginRequestDTO);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public CustomResponse<ResponseTokenDTO> refreshToken(@RequestBody @Valid final TokenRefreshRequestDTO tokenRefreshRequestDTO) {
        final ResponseTokenDTO tokenResponse =  adminRefreshTokenService.refreshToken(tokenRefreshRequestDTO);
        return CustomResponse.successOf(tokenResponse);
    }
}
