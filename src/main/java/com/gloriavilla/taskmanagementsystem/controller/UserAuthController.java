package com.gloriavilla.taskmanagementsystem.controller;
import java.util.List;

import com.gloriavilla.taskmanagementsystem.dto.TaskDto;
import com.gloriavilla.taskmanagementsystem.dto.UserDto;
import com.gloriavilla.taskmanagementsystem.model.Task;
import com.gloriavilla.taskmanagementsystem.model.User;
import com.gloriavilla.taskmanagementsystem.service.TaskService;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        if (result.hasErrors() ) {
            model.addAttribute("task", taskDto);

            return "task";
        }
        if (taskDto.getName() == null || taskDto.getName().isEmpty()  ){

            List<Task> allTasks = taskService.findAllByUserId(1L);

            model.addAttribute("allTasks", allTasks);
            model.addAttribute("success","success");
            return "task";
        }
        taskService.saveTask(taskDto);

        List<Task> allTasks = taskService.findAllByUserId(1L);

        model.addAttribute("allTasks", allTasks);
        model.addAttribute("success","success");
        //return "redirect:/users?success";
        return "task";
    }

    @GetMapping("/task/list")
    public String tasks(Model model) {
        List<Task> allTasks = taskService.findAllByUserId(1L);
        model.addAttribute("allTasks", allTasks);
        // return indicates what page will be returned
        return "/allTasks";
    }

    // handler method is used to handle a list of students
    @GetMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "task";

    }

    // I want to find a     task by id
    @GetMapping("/task/find")
    public String findTaskById(@ModelAttribute("task") TaskDto taskDto,  UserDto userDto,BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            tasks(model);
        }
        taskService.findTaskById(1L);

        List<Task> allTasks = taskService.findAllByUserId(1L);

        model.addAttribute("allTasks", allTasks);
        model.addAttribute("success", "success");
        //return "redirect:/tasks?success";
        return "/task";
        //command shift /
    }


    @PostMapping("/task/edit")
    public String editTask(@ModelAttribute("task") TaskDto taskDto,  UserDto userDto,BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("task", taskDto);

            return "/task";
        }
        //taskService.editTask(taskDto);

        //fetch the task from db
        Task updatedTask= new Task();
        updatedTask.setName(taskDto.getName());
        updatedTask.setId(taskDto.getId());
        Task currentTask = taskService.findTaskById(1L);
        taskService.updateTask(currentTask, updatedTask);

        //update this task with the new values
        //taskService.updateTask(currentTask, taskDto);

        //model.addAttribute("allTasks", allTasks);
        model.addAttribute("success", "success");
        //return "redirect:/tasks?success";
        //return "/task";
        //command shift /
        return "/taskEdit";
    }


    @PostMapping("/task/route")
    public String processRouting(@ModelAttribute("task") TaskDto taskDto, BindingResult result,
                           Model model) {
        model.addAttribute("task", taskDto);
        return "/taskEdit";
    }

    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        //model.addAttribute("success", "success");
        //return "redirect:/task/list";
        return "Deleted task with id: " + id + " successfully!";
    }




}
