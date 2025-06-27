package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.SignupRequestDTO;
import com.server.NewAggrigationServer.dto.SignupResponseDTO;
import com.server.NewAggrigationServer.dto.UserDTO;
import com.server.NewAggrigationServer.model.User;
import com.server.NewAggrigationServer.repository.UserRepository;
import com.server.NewAggrigationServer.service.SignupService;
import com.server.NewAggrigationServer.service.UserService;
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
