package com.taskify.projectservice.service;

import com.taskify.projectservice.dao.ProjectRepository;
import com.taskify.projectservice.dao.TaskRepository;
import com.taskify.projectservice.entities.Project;
import com.taskify.projectservice.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Task createTask(Task task) {
        task.setDuration(Duration.ZERO);
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }


    public Task updateTask(Task task,Long id) {
        boolean isPresent = taskRepository.findById(id).isPresent();
        if (isPresent){
            taskRepository.save(task);
        }
        else {
            throw new NotFoundException("Task not found");
        }
        return task;
    }
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Long setTaskDuration(Long id,String taskDurationInHours, String taskDurationInMinutes, String taskDurationInSeconds) {
        Task task = getTask(id);
        Duration duration = task.getDuration();
        duration = duration.plusHours(Long.parseLong(taskDurationInHours)).plusMinutes(Long.parseLong(taskDurationInMinutes)).plusSeconds(Long.parseLong(taskDurationInSeconds));
        taskRepository.save(task);
        return duration.toSeconds();
    }

    public List<Task> getAllTasksByUserId(Long userId){
        List<Project> projects = projectRepository.findByUserId(userId);
        List<Task> tasks = new ArrayList<>();
        for (Project project : projects) {
            List<Task> allByProjectId = taskRepository.findAllByProjectId(project.getId());
            tasks.addAll(allByProjectId);
        }
        return tasks;

    }
}
