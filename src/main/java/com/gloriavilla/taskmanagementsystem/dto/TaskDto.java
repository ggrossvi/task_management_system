package com.gloriavilla.taskmanagementsystem.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public class TaskDto {

    // should id have a @ID? because it is primary
    // why does UserDto have @Email annotation
    @NotEmpty
    private Long id;

    @NotEmpty
    String name;
    String description;
    Date date;

    Long userId; //name of column in user model

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
