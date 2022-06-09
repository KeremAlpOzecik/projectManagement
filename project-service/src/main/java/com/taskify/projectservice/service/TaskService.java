package com.taskify.projectservice.service;

import com.taskify.projectservice.dao.TaskRepository;
import com.taskify.projectservice.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }


    public Task updateTask(Task task,Long id) {
        taskRepository.findById(id).ifPresent(taskRepository::save);
        return task;
    }
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}
