package com.gloriavilla.taskmanagementsystem.repository;
import com.gloriavilla.taskmanagementsystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;
import java.util.Optional;

public interface TaskRepository  extends JpaRepository <Task, Long>{
    @Meta(comment = "find tasks by id")
    Optional<Task> findById(Long id);

    // using JPA query naming convention
    List<Task> findAllByUserId(Long userId);

    @Meta(comment = "delete by task id")
    void deleteById(Long id);
}


//Controller -> Service(I) => ServiceImpl => Ropository(I) => RepositoryImpl