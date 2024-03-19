package com.gloriavilla.taskmanagementsystem.repository;
import com.gloriavilla.taskmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository <Task, Long>{
    Optional<Task> findById(Long id);
    Task findByUserId(Long id); //userId is from the user one to manymodel

}
