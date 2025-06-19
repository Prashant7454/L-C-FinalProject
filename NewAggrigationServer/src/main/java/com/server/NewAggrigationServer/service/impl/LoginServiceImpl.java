package com.server.NewAggrigationServer.service.impl;

import com.server.NewAggrigationServer.dto.LoginResponseDTO;
import com.server.NewAggrigationServer.model.User;
import com.server.NewAggrigationServer.repository.UserRepository;
import com.server.NewAggrigationServer.service.LoginService;
import com.server.NewAggrigationServer.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        String encryptedInput = encryptionUtil.encrypt(rawPassword);

        if (encryptedInput.equals(user.getPassword())) {
            LoginResponseDTO dto = new LoginResponseDTO();
            dto.setMessage("Login successful");
            dto.setUserId(user.getId());
            dto.setToken("token");
            return dto;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
