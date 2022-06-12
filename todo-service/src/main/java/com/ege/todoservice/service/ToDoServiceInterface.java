package com.ege.todoservice.service;

import com.ege.todoservice.ApiResponse;
import com.ege.todoservice.model.Todo;
import com.ege.todoservice.model.dto.TodoDto;
import com.ege.todoservice.model.requests.CreateTodoRequest;
import com.ege.todoservice.model.requests.UpdateTodoRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface ToDoServiceInterface {

    List<Todo> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);
    Todo getTodoById(Long id);
    TodoDto updateTodo(Long id, UpdateTodoRequest request);
    void deleteTodo(Long id);
    ResponseEntity<ApiResponse<Todo>> saveTodo(Todo request);

    List<TodoDto> getAllByUserId(Long userId);

    TodoDto updateCompleteStatus(Long id);
}
