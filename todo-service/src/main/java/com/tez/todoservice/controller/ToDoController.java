package com.tez.todoservice.controller;

import com.tez.todoservice.feignclient.ApiResponse;
import com.tez.todoservice.model.Todo;
import com.tez.todoservice.model.dto.TodoDto;
import com.tez.todoservice.model.requests.DateBetweenRequest;
import com.tez.todoservice.model.requests.UpdateTodoRequest;
import com.tez.todoservice.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "v1/api/todo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping(value = "/{id}")
    public Todo getTodoById(@PathVariable Long id){
        return toDoService.getTodoById(id);
    }

    @PostMapping(value = "/add-todo")
    public ResponseEntity<ApiResponse<Todo>> addTodo(@RequestBody Todo request){
        return toDoService.saveTodo(request);
    }

    @PostMapping(value = "/update-todo/{id}")
    public TodoDto updateTodo(@PathVariable Long id , @RequestBody UpdateTodoRequest request){
        return toDoService.updateTodo(id, request);
    }

    @DeleteMapping(value = "/delete-todo/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        toDoService.deleteTodo(id);
        return new ResponseEntity<>(String.format("Todo with id %s deleted successfully", id), null, HttpStatus.OK);
    }

    @PostMapping(value = "/date-between/{userId}")
    public List<Todo> findByDate(@RequestBody DateBetweenRequest dateBetween, @PathVariable Long userId){
        return toDoService.findByExpiryDateBetween(dateBetween, userId);
    }

    @GetMapping(value = "/getCompletedTodosByUserId/{userId}")
    public List<TodoDto> getCompletedTodos(@PathVariable Long userId) {
        return toDoService.getCompletedTodos(userId);
    }

    @GetMapping(value = "/getAllByUserId/{userId}")
    public List<TodoDto> getAllByUserId(@PathVariable Long userId){
        return toDoService.getAllByUserId(userId);
    }

    @PatchMapping(value = "/updateCompleteStatus/{id}")
    public TodoDto updateCompleteStatus(@PathVariable Long id){
        return toDoService.updateCompleteStatus(id);
    }
}
