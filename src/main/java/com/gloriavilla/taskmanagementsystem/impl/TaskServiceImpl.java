package com.gloriavilla.taskmanagementsystem.impl;

import com.gloriavilla.taskmanagementsystem.dto.TaskDto;
import com.gloriavilla.taskmanagementsystem.model.Task;
import com.gloriavilla.taskmanagementsystem.repository.TaskRepository;
import com.gloriavilla.taskmanagementsystem.repository.UserRepository;
import com.gloriavilla.taskmanagementsystem.service.TaskService;
import com.gloriavilla.taskmanagementsystem.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<Task> findAllByUserId(Long userId) {
        List<Task> allTasks = taskRepository.findAllByUserId(userId);
        Collections.sort(allTasks, new Comparator<Task>() {
                    @Override
                    public int compare(Task o1, Task o2) {
                        return   Long.compare(o2.getId(), o1.getId());
                    }
                }


        );
        return allTasks;
    }

    public Task findTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return task.get();
        }
        return null;
    }

    public void updateTask(Task currentTask, Task dto) {
        //currentTask.setDescription(dto.getDescription());
        currentTask.setName(dto.getName());
        taskRepository.save(currentTask);
        //taskRepository.deleteById(id);
    }



    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
