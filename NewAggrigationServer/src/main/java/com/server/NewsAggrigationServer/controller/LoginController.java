package com.server.NewsAggrigationServer.controller;

import com.server.NewsAggrigationServer.dto.LoginRequestDTO;
import com.server.NewsAggrigationServer.dto.LoginResponseDTO;
import com.server.NewsAggrigationServer.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequest) {
        return loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }
}
