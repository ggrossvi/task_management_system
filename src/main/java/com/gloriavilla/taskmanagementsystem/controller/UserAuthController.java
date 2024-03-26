package com.gloriavilla.taskmanagementsystem.controller;
import java.util.List;

import com.gloriavilla.taskmanagementsystem.dto.TaskDto;
import com.gloriavilla.taskmanagementsystem.dto.UserDto;
import com.gloriavilla.taskmanagementsystem.model.User;
import com.gloriavilla.taskmanagementsystem.service.TaskService;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;

@Controller
public class UserAuthController {
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Autowired
    public UserAuthController(UserService userService)    {
        this.userService = userService;
    }
    // handler method to handle the home (index.html is home) page request
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // handler method handles the login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // handler method to handle the student registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        // create a model object to store form data

       UserDto user = new UserDto();
        model.addAttribute("user", user);

        return "register";
    }

    // handler method to handle student registration from submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                               Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", userDto);

            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";

    }

    @PostMapping("/task/save")
    public String saveTask(@ModelAttribute("task") TaskDto taskDto,  UserDto userDto,BindingResult result,
                           Model model){
        if (result.hasErrors()) {
            model.addAttribute("task", taskDto);

            return "/users";
        }
        taskService.saveTask(taskDto);
        return "redirect:/users?success";
    }

    // handler method is used to handle a list of students
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";

    }
}
