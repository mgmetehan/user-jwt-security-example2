package com.mgmetehan.userjwtsecurityexample2.admin.converter;

import com.mgmetehan.userjwtsecurityexample2.admin.dto.request.AdminRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.dto.response.ResponseAdminRegisterDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.model.Admin;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminConverter {

    public static Admin toAdmin(AdminRegisterRequestDTO dto) {
        return Admin.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
    }

    public static ResponseAdminRegisterDTO toResponseDto(Admin admin) {
        return ResponseAdminRegisterDTO.builder()
                .email(admin.getEmail())
                .password(admin.getPassword())
                .firstName(admin.getFirstName())
                .lastName(admin.getLastName())
                .phoneNumber(admin.getPhoneNumber())
                .userType(admin.getUserType())
                .userStatus(admin.getUserStatus())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}
