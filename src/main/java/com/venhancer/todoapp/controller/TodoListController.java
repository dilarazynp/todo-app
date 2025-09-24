package com.venhancer.todoapp.controller;

import com.venhancer.todoapp.entity.TodoList;
import org.springframework.web.bind.annotation.*;
import java.util.List;

public interface TodoListController {
    TodoList create(TodoList req);
    List<TodoList> GetAllTodoList();
    TodoList getTodoById(Long id);
    List<TodoList> getTodoListByParams(String title, Boolean completed);
    boolean deleteTodoList(Long id);
    TodoList updateTodo(Long id, TodoList req);
}
