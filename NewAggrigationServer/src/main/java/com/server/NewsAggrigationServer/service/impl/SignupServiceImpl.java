package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.SignupRequestDTO;
import com.server.NewsAggrigationServer.dto.SignupResponseDTO;
import com.server.NewsAggrigationServer.dto.UserDTO;
import com.server.NewsAggrigationServer.service.SignupService;
import com.server.NewsAggrigationServer.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class SignupServiceImpl implements SignupService {

    private final UserService userService;

    public SignupServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(signupRequestDTO.getEmail());
        userDTO.setUsername(signupRequestDTO.getUsername());
        userDTO.setPassword(signupRequestDTO.getPassword());
        userDTO.setRole(signupRequestDTO.getRole());

        UserDTO resDTO = userService.createUser(userDTO);

        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        signupResponseDTO.setMessage("User Created Successfully...");
        signupResponseDTO.setStatus(200);
        signupResponseDTO.setUserId(resDTO.getId());
        return signupResponseDTO;
    }
}
