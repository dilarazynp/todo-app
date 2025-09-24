package com.venhancer.todoapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoListResponseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime created;
    private LocalDateTime updated;

}
