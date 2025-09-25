package com.venhancer.todoapp.services.impl;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import com.venhancer.todoapp.entity.TodoList;
import com.venhancer.todoapp.exception.DuplicateException;
import com.venhancer.todoapp.exception.NotFoundException;
import com.venhancer.todoapp.mapper.TodoListMapper;
import com.venhancer.todoapp.repository.TodoListRepository;
import com.venhancer.todoapp.services.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListMapper todoListMapper;
    private final TodoListRepository repo;

    public TodoListServiceImpl(TodoListMapper todoListMapper, TodoListRepository repo) {
        this.todoListMapper = todoListMapper;
        this.repo = repo;
    }

    @Override
    public TodoListResponseDTO createTodoList(TodoListRequestDTO req) {

        if (repo.existsByTitle(req.getTitle())) {
            throw new DuplicateException("Todo list with this title already exists: " + req.getTitle());
        }
        TodoList entity = todoListMapper.toEntity(req);
        TodoList saved  = repo.save(entity);
        return todoListMapper.toDto(saved);
    }

    @Override
    public List<TodoListResponseDTO> getAllTodoList() {
        return todoListMapper.toDtoList(repo.findAll());
    }

    @Override
    public TodoListResponseDTO getTodoList(Long id) {
        TodoList todoItem = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo list not found with id: " + id));
        return todoListMapper.toDto(todoItem);
    }

    @Override
    public List<TodoListResponseDTO> getTodoListWithParams(String title, Boolean completed) {
        List<TodoList> result;

        boolean hasTitle = title != null && !title.isBlank();
        if (hasTitle && completed != null) {
            result = repo.findByTitleContainingAndCompleted(title, completed);
        } else if (hasTitle) {
            result = repo.findByTitleContaining(title);
        } else if (completed != null) {
            result = repo.findByCompleted(completed);
        } else {
            result = repo.findAll();
        }

        return todoListMapper.toDtoList(result);
    }

    @Override
    public void deleteTodoList(Long id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException("Todo list not found with id: " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public TodoListResponseDTO updateTodoList(Long id, TodoListRequestDTO req) {
        TodoList todoItem = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Todo list not found with id: " + id));

        boolean exists = repo.existsByTitle(req.getTitle());
        if (exists && !todoItem.getTitle().equals(req.getTitle())) {
            throw new DuplicateException("Todo list already exists with title: " + req.getTitle());
        }

        todoItem.setTitle(req.getTitle());
        todoItem.setDescription(req.getDescription());
        todoItem.setCompleted(req.isCompleted());

        return todoListMapper.toDto(repo.save(todoItem));
    }
}
