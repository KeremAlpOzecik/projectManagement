package com.taskify.projectservice.controller;

import com.taskify.projectservice.entities.Task;
import com.taskify.projectservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    @GetMapping("/all")
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity getTaskById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTask(@RequestBody Task task, @RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.updateTask(task,id));
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
    }
}*/


