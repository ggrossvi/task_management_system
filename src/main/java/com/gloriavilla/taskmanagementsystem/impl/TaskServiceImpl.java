package com.gloriavilla.taskmanagementsystem.impl;

import com.gloriavilla.taskmanagementsystem.dto.TaskDto;
import com.gloriavilla.taskmanagementsystem.model.Task;
import com.gloriavilla.taskmanagementsystem.repository.TaskRepository;
import com.gloriavilla.taskmanagementsystem.repository.UserRepository;
import com.gloriavilla.taskmanagementsystem.service.TaskService;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        super();
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public void saveTask(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setUserId(1L);
        // add more attributes after basic functionality working
        taskRepository.save(task);
    }

    @Override
    public List<Task> findTaskById(Long id) {
        return null;
    }
}
