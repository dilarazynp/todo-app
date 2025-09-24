package com.venhancer.todoapp.controller.impl;

import com.venhancer.todoapp.entity.TodoList;
import com.venhancer.todoapp.services.TodoListService;
import com.venhancer.todoapp.controller.TodoListController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/todo")
public class TodoListControllerImpl implements TodoListController {

    private final TodoListService todoListService;

    public TodoListControllerImpl(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    @Override
    @PostMapping(path = "/list")
    public TodoList create(@RequestBody TodoList req) {
        return todoListService.create(req);
    }

    @Override
    @GetMapping(path="/list")
    public List<TodoList> GetAllTodoList() {
        return todoListService.getAllTodoList();
    }

    @Override
    @GetMapping(path="/list/{id}")
    public TodoList getTodoById(@PathVariable Long id){
        return todoListService.get(id);
    }

    @Override
    @GetMapping(path = "/list/with-params")
    public List<TodoList> getTodoListByParams(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "completed", required = false) Boolean completed) {
        return todoListService.getTodoListWithParams(title, completed);
    }

    @Override
    @DeleteMapping(path="/delete-list/{id}")
    public boolean deleteTodoList(@PathVariable Long id) {
        return todoListService.deleteTodoList(id);
    }

    @Override
    @PutMapping(path="/update-list/{id}")
    public TodoList updateTodo(@PathVariable Long id, @RequestBody TodoList req) {
        return todoListService.update(id, req);
    }
}
