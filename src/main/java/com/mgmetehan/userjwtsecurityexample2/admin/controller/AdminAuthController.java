package com.mgmetehan.userjwtsecurityexample2.admin.controller;

import com.mgmetehan.userjwtsecurityexample2.admin.dto.request.AdminRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import com.mgmetehan.userjwtsecurityexample2.service.AdminRegisterService;
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

    @PostMapping("/register")
    public CustomResponse<Void> registerAdmin(@RequestBody @Valid final AdminRegisterRequestDTO adminRegisterRequestDTO) {
        adminRegisterService.registerAdmin(adminRegisterRequestDTO);
        return CustomResponse.SUCCESS;
    }
}
