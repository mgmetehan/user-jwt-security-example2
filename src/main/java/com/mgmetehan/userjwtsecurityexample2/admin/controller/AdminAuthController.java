package com.mgmetehan.userjwtsecurityexample2.admin.controller;

import com.mgmetehan.userjwtsecurityexample2.admin.dto.request.AdminRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminLoginService;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminLogoutService;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminRefreshTokenService;
import com.mgmetehan.userjwtsecurityexample2.admin.service.AdminRegisterService;
import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.LoginRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.TokenInvalidateRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.request.TokenRefreshRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.common.dto.response.ResponseTokenDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final AdminLogoutService adminLogoutService;

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
        final ResponseTokenDTO tokenResponse = adminRefreshTokenService.refreshToken(tokenRefreshRequestDTO);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> logout(@RequestBody @Valid final TokenInvalidateRequestDTO tokenInvalidateRequestDTO) {
        adminLogoutService.logout(tokenInvalidateRequestDTO);
        return CustomResponse.SUCCESS;
    }
}
