package com.gloriavilla.taskmanagementsystem.service;
import com.gloriavilla.taskmanagementsystem.dto.UserDto;
import com.gloriavilla.taskmanagementsystem.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();

}
