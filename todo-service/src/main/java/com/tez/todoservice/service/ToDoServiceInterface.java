package com.tez.todoservice.service;

import com.tez.todoservice.feignclient.ApiResponse;
import com.tez.todoservice.model.Todo;
import com.tez.todoservice.model.dto.TodoDto;
import com.tez.todoservice.model.requests.DateBetweenRequest;
import com.tez.todoservice.model.requests.UpdateTodoRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ToDoServiceInterface {

    List<Todo> findByExpiryDateBetween(DateBetweenRequest request, Long userId);
    Todo getTodoById(Long id);
    TodoDto updateTodo(Long id, UpdateTodoRequest request);
    void deleteTodo(Long id);
    ResponseEntity<ApiResponse<Todo>> saveTodo(Todo request);

    List<TodoDto> getAllByUserId(Long userId);

    TodoDto updateCompleteStatus(Long id);
}
