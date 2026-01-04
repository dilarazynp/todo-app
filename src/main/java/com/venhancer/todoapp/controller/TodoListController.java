package com.venhancer.todoapp.controller;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import com.venhancer.todoapp.services.TodoListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rest/api/todo")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/add-list")
    public ResponseEntity<TodoListResponseDTO> create(@RequestBody TodoListRequestDTO todoListRequestDTO){
      TodoListResponseDTO response = todoListService.createTodoList(todoListRequestDTO);
      return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoListResponseDTO>> getAll() {
        System.out.println("DEPLOY TEST");
        return ResponseEntity.ok(todoListService.getAllTodoList());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<TodoListResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(todoListService.getTodoList(id));
    }

    @GetMapping("/list/with-params")
    public ResponseEntity<List<TodoListResponseDTO>> getByParams(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed
    ) {
        return ResponseEntity.ok(todoListService.getTodoListWithParams(title, completed));
    }

    @DeleteMapping("/delete-list/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update-list/{id}")
    public ResponseEntity<TodoListResponseDTO> update(
            @PathVariable Long id,
            @RequestBody TodoListRequestDTO req
    ) {
        return ResponseEntity.ok(todoListService.updateTodoList(id, req));
    }
}
