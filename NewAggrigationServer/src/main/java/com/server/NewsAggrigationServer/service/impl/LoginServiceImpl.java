package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.LoginResponseDTO;
import com.server.NewsAggrigationServer.exception.ExceptionConstants;
import com.server.NewsAggrigationServer.exception.UserLoginException;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.service.LoginService;
import com.server.NewsAggrigationServer.util.EncryptionUtil;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final EncryptionUtil encryptionUtil;

    public LoginServiceImpl(UserRepository userRepository, EncryptionUtil encryptionUtil) {
        this.userRepository = userRepository;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public LoginResponseDTO login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserLoginException(ExceptionConstants.INVALID_USERNAME_PASSWORD));

        String encryptedInput = encryptionUtil.encrypt(rawPassword);

        if (encryptedInput.equals(user.getPassword())) {
            LoginResponseDTO dto = new LoginResponseDTO();
            dto.setMessage("Login successful");
            dto.setUserId(user.getId());
            dto.setToken("token");
            dto.setRole(user.getRole());
            return dto;
        } else {
            throw new UserLoginException(ExceptionConstants.INVALID_USERNAME_PASSWORD);
        }
    }
}
