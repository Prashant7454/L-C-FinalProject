package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.LoginResponseDTO;

public interface LoginService {
    LoginResponseDTO login(String username, String rawPassword);
}
