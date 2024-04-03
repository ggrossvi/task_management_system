package com.gloriavilla.taskmanagementsystem.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(nullable=false)
    String name;
    String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "date")
    Date date;
//   @ManyToOne
//   @JoinColumn(name="user_id", nullable=false)  // @JoinColumn indicates the mapping on the owning side
       //User user;
    Long userId; //name of column in user model

    public enum Priority {
        URGENT_IMPORTANT,
        NOT_URGENT_IMPORTANT,
        URGENT_NOT_IMPORTANT,
        NOT_URGENT_NOT_IMPORTANT
    }


}
