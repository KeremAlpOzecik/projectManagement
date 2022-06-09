package com.ege.todoservice.service;

import com.ege.todoservice.ApiResponse;
import com.ege.todoservice.User;
import com.ege.todoservice.UserFeignClient;
import com.ege.todoservice.model.Todo;
import com.ege.todoservice.model.dto.TodoDto;
import com.ege.todoservice.model.requests.CreateTodoRequest;
import com.ege.todoservice.model.requests.UpdateTodoRequest;
import com.ege.todoservice.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Log4j2

public class ToDoService implements ToDoServiceInterface{

    private final ToDoRepository toDoRepository;
    @Autowired
    private UserFeignClient userFeignClient;
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @Override
    public List<Todo> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Todo> findByExpiryDateBetween = toDoRepository.findAllByDateBetween(startDate, endDate);
        System.out.println("findByExpiryDateBetween = " + findByExpiryDateBetween);
        return findByExpiryDateBetween;
    }

    @Override
    public Todo getTodoById(Long id) {
        return toDoRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Todo not found by id"));
    }

    @Override
    public TodoDto updateTodo(Long id, UpdateTodoRequest request) {
        Todo todo = toDoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Todo is not fount for update operation!"));
        todo.setCompleted(request.getCompleted());
        todo.setContent(request.getContent());
        todo.setDate(request.getDate());
        return mapTodoDto(toDoRepository.save(todo));
    }


    @Override
    public void deleteTodo(Long id) {
        Optional<Todo> todo = toDoRepository.findById(id);
        if (todo.isPresent()){
            toDoRepository.delete(todo.get());
        }
    }

/*    @Override
    public TodoDto saveTodo(CreateTodoRequest request) {
        Todo todo = new Todo();
        todo.setDate(LocalDate.now());
        todo.setCompleted(false);
        todo.setContent(request.getContent());
        todo.setUserId(request.getUserId());
        return mapTodoDto(toDoRepository.save(todo));
    }*/

    @Transactional()
    public ResponseEntity<ApiResponse<Todo>> saveTodo(Todo todo1) {
        log.info("Entered" + getClass().getName());
        String transactionId = UUID.randomUUID().toString();
        Todo todo = null;
        User user = getUserDetails(todo1);
        todo = todo1;
        if (user != null)
            todo.setUserId(user.getId());

        ResponseEntity<ApiResponse<Todo>> responseEntity = new ResponseEntity<>(
                new ApiResponse<>(toDoRepository.save(todo1), new Date(), "Todo Created"),
                HttpStatus.CREATED);
        return responseEntity;
    }
    public User getUserDetails(Todo todo) {
        return userFeignClient.getUserByUserId(todo.getUserId()).getBody().getData();
    }


    private TodoDto mapTodoDto(Todo todo) {
        TodoDto dto = new TodoDto();
        dto.setId(todo.getId());
        dto.setContent(todo.getContent());
        dto.setDate(todo.getDate());
        dto.setCompleted(todo.getCompleted());
        dto.setUserId(todo.getUserId());
        return dto;
    }


}
