package com.mgmetehan.userjwtsecurityexample2.user.service;

import com.mgmetehan.userjwtsecurityexample2.core.exception.ErrorStatusCode;
import com.mgmetehan.userjwtsecurityexample2.core.exception.GeneralException;
import com.mgmetehan.userjwtsecurityexample2.user.converter.UserConverter;
import com.mgmetehan.userjwtsecurityexample2.user.dto.request.UserRegisterRequestDTO;
import com.mgmetehan.userjwtsecurityexample2.user.dto.response.ResponseUserRegisterDTO;
import com.mgmetehan.userjwtsecurityexample2.user.model.User;
import com.mgmetehan.userjwtsecurityexample2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseUserRegisterDTO registerUser(UserRegisterRequestDTO userRegisterRequest) {
        if (userRepository.existsUserByEmail(userRegisterRequest.getEmail())) {
            throw new GeneralException(ErrorStatusCode.ALREADY_EXIST, "The email is already used for another admin : " + userRegisterRequest.getEmail());
        }

        final User userEntityToBeSave = UserConverter.toEntity(userRegisterRequest);

        userEntityToBeSave.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));

        User savedUserEntity = userRepository.save(userEntityToBeSave);

        return UserConverter.convertToResponseDTO(savedUserEntity);
    }
}
