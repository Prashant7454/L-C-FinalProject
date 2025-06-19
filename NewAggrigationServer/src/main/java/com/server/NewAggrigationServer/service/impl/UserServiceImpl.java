package com.server.NewAggrigationServer.service.impl;


import com.server.NewAggrigationServer.dto.LoginResponseDTO;
import com.server.NewAggrigationServer.dto.UserDTO;
import com.server.NewAggrigationServer.exception.FoundDuplicateUserNameException;
import com.server.NewAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewAggrigationServer.model.User;
import com.server.NewAggrigationServer.repository.UserRepository;
import com.server.NewAggrigationServer.service.UserService;
import com.server.NewAggrigationServer.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EncryptionUtil encryptionUtil;

    public UserServiceImpl(UserRepository userRepository, EncryptionUtil encryptionUtil) {
        this.userRepository = userRepository;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encryptionUtil.encrypt(userDTO.getPassword()));
        user.setRole(userDTO.getRole());

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new FoundDuplicateUserNameException("Email or Username already exists");
        }

        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getRole());
            dto.setPassword("***");
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: "+email));

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        return dto;
    }
}

