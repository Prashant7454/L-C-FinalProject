package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.SignupRequestDTO;
import com.server.NewsAggrigationServer.dto.SignupResponseDTO;

public interface SignupService {
    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
}
