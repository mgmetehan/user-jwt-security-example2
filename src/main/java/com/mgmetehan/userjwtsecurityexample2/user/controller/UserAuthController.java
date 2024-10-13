package com.mgmetehan.userjwtsecurityexample2.user.controller;


import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.UserRegisterRequestDTO;
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

    @PostMapping("/register")
    public CustomResponse<Void> registerUser(@RequestBody @Valid final UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegisterService.registerUser(userRegisterRequestDTO);
        return CustomResponse.SUCCESS;
    }

}
