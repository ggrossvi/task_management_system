package com.gloriavilla.taskmanagementsystem.repository;
import com.gloriavilla.taskmanagementsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
