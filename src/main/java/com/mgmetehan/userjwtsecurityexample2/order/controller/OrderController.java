package com.mgmetehan.userjwtsecurityexample2.order.controller;

import com.mgmetehan.userjwtsecurityexample2.core.model.response.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Validated
public class OrderController {

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('USER')")
    public CustomResponse<String> getUserString() {
        return CustomResponse.successOf("This is USER!");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> getAdminString() {
        return CustomResponse.successOf("This is ADMIN!");
    }

    @GetMapping("/welcome")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<String> welcome() {
        return CustomResponse.successOf("Hello World!");
    }
}
