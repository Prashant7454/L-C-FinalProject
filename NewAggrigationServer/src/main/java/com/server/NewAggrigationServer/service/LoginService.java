package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(String username, String rawPassword);
}
