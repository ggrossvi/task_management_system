package com.gloriavilla.taskmanagementsystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Goal {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long goalId;
    String name;
    /*
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name ="goals_tasks",
            joinColumns = {@JoinColumn(name = "goal_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id ", referencedColumnName = "id"
            )})
    public List<Task> tasks = new ArrayList<>();

     */


}
