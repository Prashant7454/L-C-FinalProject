package com.server.NewAggrigationServer.service;

import com.server.NewAggrigationServer.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserByEmail(String email);
}