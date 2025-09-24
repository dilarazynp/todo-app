package com.venhancer.todoapp.controller;

import com.venhancer.todoapp.entity.TodoList;
import com.venhancer.todoapp.services.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/todo")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/list")
    public TodoList create(@RequestBody TodoList req) {
        return todoListService.create(req);
    }

    @GetMapping("/list")
    public List<TodoList> getAllTodoList() {
        return todoListService.getAllTodoList();
    }

    @GetMapping("/list/{id}")
    public TodoList getTodoById(@PathVariable Long id) {
        return todoListService.get(id);
    }

    @GetMapping("/list/with-params")
    public List<TodoList> getTodoListByParams(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "completed", required = false) Boolean completed) {
        return todoListService.getTodoListWithParams(title, completed);
    }

    @DeleteMapping("/delete-list/{id}")
    public boolean deleteTodoList(@PathVariable Long id) {
        return todoListService.deleteTodoList(id);
    }

    @PutMapping("/update-list/{id}")
    public TodoList updateTodo(@PathVariable Long id, @RequestBody TodoList req) {
        return todoListService.update(id, req);
    }
}
