package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.SignupRequestDTO;
import com.server.NewAggrigationServer.dto.SignupResponseDTO;

public interface SignupService {
    SignupResponseDTO signup(SignupRequestDTO signupRequestDTO);
}
