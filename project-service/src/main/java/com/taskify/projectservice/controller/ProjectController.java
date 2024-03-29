package com.taskify.projectservice.controller;

import com.taskify.projectservice.entities.Project;
import com.taskify.projectservice.entities.Task;
import com.taskify.projectservice.service.ProjectService;
import com.taskify.projectservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/v1/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final TaskService taskService;
    @PostMapping("/create")
    public ResponseEntity createProject(@RequestBody Project project) {
           return ResponseEntity.ok(projectService.createProject(project));
    }
    @GetMapping("/{id}")
    public ResponseEntity getProject(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getProject(id));
    }
    @GetMapping("user/{id}")
    public ResponseEntity getProjectsByUserId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getAllProjectsByUserId(id));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getProjectTasks(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getProjectTasks(id));
    }
    @GetMapping("/all")
    public ResponseEntity getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProject(@PathVariable("id") Long id, @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(project,id));
    }
    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
     projectService.deleteProject(id);
    }


    @GetMapping("/task/all")
    public ResponseEntity getAllTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity getTaskById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.getTask(id));
    }
    @GetMapping("/task/all/userid")
    public ResponseEntity getAllTasksByUserId(@RequestParam("userid") Long userId) {
        return ResponseEntity.ok(taskService.getAllTasksByUserId(userId));
    }

    @GetMapping("/task/all/kanban/userid")
    public ResponseEntity getAllTasksByUserIdKanban(@RequestParam("userid") Long userId) {
        return ResponseEntity.ok(taskService.getAllTasksByUserIdKanban(userId));
    }

    @PostMapping("/task/add")
    public ResponseEntity addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/task/update")
    public ResponseEntity updateTask(@RequestBody Task task, @RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.updateTask(task,id));
    }

    @DeleteMapping("/task/delete")
    public void deleteTask(@RequestParam("id") Long id) {
        taskService.deleteTask(id);
    }

    @PatchMapping("/task/update/activity")
    public ResponseEntity updateTaskDuration(@RequestParam("activity")Long activity , @RequestParam("id") Long id) {
        return ResponseEntity.ok(taskService.setTaskActivity(id,activity));
    }
}
