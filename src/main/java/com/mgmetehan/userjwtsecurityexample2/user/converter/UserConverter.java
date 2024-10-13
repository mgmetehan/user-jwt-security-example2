package com.mgmetehan.userjwtsecurityexample2.user.converter;

import com.mgmetehan.userjwtsecurityexample2.user.dto.request.UserRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseUserRegisterDTO;
import com.mgmetehan.userjwtsecurityexample2.user.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserConverter {

    public static User toEntity(UserRegisterRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        return User.builder()
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .phoneNumber(requestDTO.getPhoneNumber())
                .build();
    }

    public ResponseUserRegisterDTO convertToResponseDTO(User user) {
        return ResponseUserRegisterDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .userType(user.getUserType())
                .userStatus(user.getUserStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
