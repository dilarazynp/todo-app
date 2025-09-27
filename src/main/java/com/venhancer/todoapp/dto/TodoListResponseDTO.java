package com.venhancer.todoapp.dto;

import java.time.LocalDateTime;

public record TodoListResponseDTO(
        Long id,
        String title,
        String description,
        boolean completed,
        LocalDateTime created,
        LocalDateTime updated
) {}
