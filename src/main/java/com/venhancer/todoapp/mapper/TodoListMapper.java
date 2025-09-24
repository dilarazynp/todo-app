package com.venhancer.todoapp.mapper;

import com.venhancer.todoapp.dto.TodoListRequestDTO;
import com.venhancer.todoapp.dto.TodoListResponseDTO;
import com.venhancer.todoapp.entity.TodoList;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoListMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "updated", ignore = true)
    TodoList toEntity(TodoListRequestDTO dto);

    TodoListResponseDTO toDto(TodoList entity);

    List<TodoListResponseDTO> toDtoList(List<TodoList> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TodoListRequestDTO dto, @MappingTarget TodoList entity);

}
