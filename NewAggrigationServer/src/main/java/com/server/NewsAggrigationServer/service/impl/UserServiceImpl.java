package com.server.NewsAggrigationServer.service.impl;


import com.server.NewsAggrigationServer.dto.UserDTO;
import com.server.NewsAggrigationServer.exception.DatabaseException;
import com.server.NewsAggrigationServer.exception.ExceptionConstants;
import com.server.NewsAggrigationServer.exception.FoundDuplicateUserNameException;
import com.server.NewsAggrigationServer.exception.ResourceNotFoundException;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.service.UserService;
import com.server.NewsAggrigationServer.util.EncryptionUtil;
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
            throw new FoundDuplicateUserNameException(ExceptionConstants.USER_ALREADY_EXISTS);
        } catch (Exception ex) {
            throw new DatabaseException(
                ExceptionConstants.DB_QUERY_ERROR,
                "CREATE_USER",
                "users",
                "DB_CREATE_ERROR"
            );
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
                .orElseThrow(() -> new ResourceNotFoundException(ExceptionConstants.USER_NOT_FOUND + " with email: " + email));

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        return dto;
    }
}

