package com.server.NewAggrigationServer.controller;

import com.server.NewAggrigationServer.dto.LoginRequestDTO;
import com.server.NewAggrigationServer.dto.LoginResponseDTO;
import com.server.NewAggrigationServer.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
