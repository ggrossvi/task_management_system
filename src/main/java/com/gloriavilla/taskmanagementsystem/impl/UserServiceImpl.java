package com.gloriavilla.taskmanagementsystem.impl;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.gloriavilla.taskmanagementsystem.dto.UserDto;
import com.gloriavilla.taskmanagementsystem.model.Role;
import com.gloriavilla.taskmanagementsystem.model.User;
import com.gloriavilla.taskmanagementsystem.repository.RoleRepository;
import com.gloriavilla.taskmanagementsystem.repository.UserRepository;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                              PasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getFirstName() + " " +    userDto.getLastName());
        user.setEmail(userDto.getEmail());

        // Encrypt the password using Spring Security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }
    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }
    @Override
    public List<UserDto> findAllUsers() {
        List<User>users = userRepository.findAll();

        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }
    private UserDto mapToUserDto(User user) {
       UserDto userDto = new UserDto();

        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
