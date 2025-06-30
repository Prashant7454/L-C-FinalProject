package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.SignupRequestDTO;
import com.server.NewsAggrigationServer.dto.SignupResponseDTO;
import com.server.NewsAggrigationServer.service.SignupService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class SignupController {
    private final SignupService signupService;
    public SignupController(SignupService signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return signupService.signup(signupRequestDTO);
    }
}
