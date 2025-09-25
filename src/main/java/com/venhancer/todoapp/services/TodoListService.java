package com.venhancer.todoapp.services;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import java.util.List;

public interface TodoListService {
    TodoListResponseDTO createTodoList(TodoListRequestDTO req);
    List<TodoListResponseDTO> getAllTodoList();
    TodoListResponseDTO getTodoList(Long id);
    List<TodoListResponseDTO> getTodoListWithParams(String title, Boolean completed);
    void deleteTodoList(Long id);
    TodoListResponseDTO updateTodoList(Long id, TodoListRequestDTO req);
}
