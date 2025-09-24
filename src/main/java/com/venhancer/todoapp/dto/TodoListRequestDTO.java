package com.venhancer.todoapp.dto;
import lombok.Data;

@Data
public class TodoListRequestDTO {

    private String title;
    private String description;
    private boolean completed;
}
