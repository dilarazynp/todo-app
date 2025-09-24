package com.venhancer.todoapp.services;

import com.venhancer.todoapp.entity.TodoList;
import java.util.List;

public interface TodoListService {
    TodoList create(TodoList req);
    List<TodoList> getAllTodoList();
    TodoList get(Long id);
    List<TodoList> getTodoListWithParams(String title, Boolean completed);
    boolean deleteTodoList(Long id);
    TodoList update(Long id, TodoList req);
}
