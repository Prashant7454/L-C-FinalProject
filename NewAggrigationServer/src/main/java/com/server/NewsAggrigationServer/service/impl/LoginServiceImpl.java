package com.server.NewsAggrigationServer.service.impl;

import com.server.NewsAggrigationServer.dto.LoginResponseDTO;
import com.server.NewsAggrigationServer.exception.ExceptionConstants;
import com.server.NewsAggrigationServer.exception.UserLoginException;
import com.server.NewsAggrigationServer.model.User;
import com.server.NewsAggrigationServer.repository.UserRepository;
import com.server.NewsAggrigationServer.service.LoginService;
import com.server.NewsAggrigationServer.util.EncryptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final UserRepository userRepository;
    private final EncryptionUtil encryptionUtil;

    public LoginServiceImpl(UserRepository userRepository, EncryptionUtil encryptionUtil) {
        this.userRepository = userRepository;
        this.encryptionUtil = encryptionUtil;
    }

    @Override
    public LoginResponseDTO login(String username, String rawPassword) {
        log.info("Login attempt for username: {}", username);
        try {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UserLoginException(ExceptionConstants.INVALID_USERNAME_PASSWORD));

            String encryptedInput = encryptionUtil.encrypt(rawPassword);

            if (encryptedInput.equals(user.getPassword())) {
                LoginResponseDTO dto = new LoginResponseDTO();
                dto.setMessage("Login successful");
                dto.setUserId(user.getId());
                dto.setToken("token");
                dto.setRole(user.getRole());
                log.info("Login successful for username: {}", username);
                return dto;
            } else {
                log.error("Error while login");
                throw new UserLoginException(ExceptionConstants.INVALID_USERNAME_PASSWORD);
            }
        } catch (Exception ex) {
            log.warn("Login failed for username: {}. Error: {}", username, ex.getMessage());
            throw new UserLoginException(ExceptionConstants.LOGIN_FAILURE);
        }
    }
}
