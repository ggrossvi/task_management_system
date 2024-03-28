package com.gloriavilla.taskmanagementsystem.service;

import com.gloriavilla.taskmanagementsystem.dto.TaskDto;
import com.gloriavilla.taskmanagementsystem.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public void saveTask(TaskDto taskDto);
    public List<Task> findTaskById(Long id);
    List<Task> findAllByUserId(Long userId);
}
