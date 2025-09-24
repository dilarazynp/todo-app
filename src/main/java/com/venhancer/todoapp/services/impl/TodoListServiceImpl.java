package com.venhancer.todoapp.services.impl;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import com.venhancer.todoapp.entity.TodoList;
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
    public TodoListResponseDTO save(TodoListRequestDTO req) {
        TodoList entity = todoListMapper.toEntity(req);
        TodoList saved  = repo.save(entity);
        return todoListMapper.toDto(saved);
    }

    @Override
    public List<TodoListResponseDTO> getAllTodoList() {
        return todoListMapper.toDtoList(repo.findAll());
    }

    @Override
    public TodoListResponseDTO get(Long id) {
        TodoList e = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
        return todoListMapper.toDto(e);
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
    public boolean deleteTodoList(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public TodoListResponseDTO update(Long id, TodoListRequestDTO req) {
        TodoList e = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
        e.setTitle(req.getTitle());
        e.setDescription(req.getDescription());
        e.setCompleted(req.isCompleted());
        return todoListMapper.toDto(repo.save(e));
    }
}
