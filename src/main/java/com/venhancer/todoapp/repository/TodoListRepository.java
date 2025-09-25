package com.venhancer.todoapp.repository;

import com.venhancer.todoapp.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {


    boolean existsByTitle(String title);

    List<TodoList> findByCompleted(boolean completed);

    List<TodoList> findByTitleContaining(String title);

    List<TodoList> findByTitleContainingAndCompleted(String title, boolean completed);

}
