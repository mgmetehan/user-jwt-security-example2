package com.mgmetehan.userjwtsecurityexample2.service;

import com.mgmetehan.userjwtsecurityexample2.admin.converter.AdminConverter;
import com.mgmetehan.userjwtsecurityexample2.admin.dto.request.AdminRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.dto.response.ResponseAdminRegisterDTO;
import com.mgmetehan.userjwtsecurityexample2.admin.model.Admin;
import com.mgmetehan.userjwtsecurityexample2.admin.repository.AdminRepository;
import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminRegisterService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseAdminRegisterDTO registerAdmin(AdminRegisterRequestDTO adminRegisterRequestDTO) {

        if (adminRepository.existsAdminByEmail(adminRegisterRequestDTO.getEmail())) {
            throw new GeneralException(ErrorStatusCode.ALREADY_EXIST,
                    "The email is already used for another admin : " + adminRegisterRequestDTO.getEmail());
        }

        final Admin adminToBeSave = AdminConverter.toAdmin(adminRegisterRequestDTO);

        adminToBeSave.setPassword(passwordEncoder.encode(adminRegisterRequestDTO.getPassword()));

        Admin savedAdminEntity = adminRepository.save(adminToBeSave);

        return AdminConverter.toResponseDto(savedAdminEntity);
    }
}
