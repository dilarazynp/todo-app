package com.venhancer.todoapp.repository;

import com.venhancer.todoapp.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {

    List<TodoList> findByCompleted(boolean completed);
}
