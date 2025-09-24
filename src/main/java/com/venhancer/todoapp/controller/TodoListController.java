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
    public ResponseEntity<TodoListResponseDTO> save(@RequestBody TodoListRequestDTO todoListRequestDTO){
      TodoListResponseDTO response = todoListService.save(todoListRequestDTO);
      return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TodoListResponseDTO>> getAllTodoList() {
        return ResponseEntity.ok(todoListService.getAllTodoList());
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<TodoListResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(todoListService.get(id));
    }

    @GetMapping("/list/with-params")
    public ResponseEntity<List<TodoListResponseDTO>> getToDoListByParams(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed
    ) {
        return ResponseEntity.ok(todoListService.getTodoListWithParams(title, completed));
    }

    @DeleteMapping("/delete-list/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Long id) {
        boolean deleted = todoListService.deleteTodoList(id);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/update-list/{id}")
    public ResponseEntity<TodoListResponseDTO> update(
            @PathVariable Long id,
            @RequestBody TodoListRequestDTO req
    ) {
        return ResponseEntity.ok(todoListService.update(id, req));
    }
}
