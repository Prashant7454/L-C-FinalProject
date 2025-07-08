package com.server.NewsAggrigationServer.service;

import com.server.NewsAggrigationServer.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserByEmail(String email);
}