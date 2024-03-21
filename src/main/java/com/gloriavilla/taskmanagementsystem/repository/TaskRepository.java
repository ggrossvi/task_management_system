package com.gloriavilla.taskmanagementsystem.repository;
import com.gloriavilla.taskmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository <Task, Long>{
    Optional<Task> findById(Long id);


}
