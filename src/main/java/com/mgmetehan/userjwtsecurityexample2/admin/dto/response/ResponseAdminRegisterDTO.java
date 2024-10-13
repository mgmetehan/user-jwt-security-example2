package com.mgmetehan.userjwtsecurityexample2.admin.dto.response;

import com.mgmetehan.userjwtsecurityexample2.auth.model.type.UserStatus;
import com.mgmetehan.userjwtsecurityexample2.auth.model.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAdminRegisterDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Builder.Default
    private UserType userType = UserType.ADMIN;
    @Builder.Default
    private UserStatus userStatus = UserStatus.ACTIVE;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}