package com.venhancer.todoapp.services.impl;

import com.venhancer.todoapp.entity.TodoList;
import com.venhancer.todoapp.repository.TodoListRepository;
import com.venhancer.todoapp.services.TodoListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository repo;

    public TodoListServiceImpl(TodoListRepository repo) {
        this.repo = repo;
    }

    @Override
    public TodoList create(TodoList req) {
        if (req.getTitle() == null || req.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        return repo.save(req);
    }

    @Override
    public List<TodoList> getAllTodoList() {
        return repo.findAll();
    }

    @Override
    public TodoList get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found: " + id));
    }

    @Override
    public List<TodoList> getTodoListWithParams(String title, Boolean completed) {
        if (title != null && completed != null) {
            return repo.findByTitleContainingAndCompleted(title, completed);
        } else if (title != null) {
            return repo.findByTitleContaining(title);
        } else if (completed != null) {
            return repo.findByCompleted(completed);
        } else {
            return repo.findAll();
        }
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
    public TodoList update(Long id, TodoList req) {
        TodoList e = get(id);
        if (req.getTitle() != null)       e.setTitle(req.getTitle());
        if (req.getDescription() != null) e.setDescription(req.getDescription());
        e.setCompleted(req.isCompleted());
        return repo.save(e);
    }
}
