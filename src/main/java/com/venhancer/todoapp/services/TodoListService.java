package com.venhancer.todoapp.services;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import java.util.List;

public interface TodoListService {
    TodoListResponseDTO save(TodoListRequestDTO req);
    List<TodoListResponseDTO> getAllTodoList();
    TodoListResponseDTO get(Long id);
    List<TodoListResponseDTO> getTodoListWithParams(String title, Boolean completed);
    boolean deleteTodoList(Long id);
    TodoListResponseDTO update(Long id, TodoListRequestDTO req);
}
