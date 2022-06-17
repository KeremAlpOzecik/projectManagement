package com.taskify.projectservice.service;

import com.taskify.projectservice.dao.ProjectRepository;
import com.taskify.projectservice.dao.TaskRepository;
import com.taskify.projectservice.entities.AllTaskByUserId;
import com.taskify.projectservice.entities.Project;
import com.taskify.projectservice.entities.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Task createTask(Task task) {
        task.setActivity(0L);
        return taskRepository.save(task);
    }

    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).orElse(null);
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findAllByProjectId(projectId);
    }


    public Task updateTask(Task task, Long id) {
        Task task1 = taskRepository.findById(id).orElse(null);

        if (task1 != null) {
            task1.setActivity(task.getActivity());
            task1.setDescription(task.getDescription());
            task1.setStatus(task.getStatus());
            task1.setProjectId(task.getProjectId());
            task1.setStartDate(task.getStartDate());
            task1.setEndDate(task.getEndDate());
            taskRepository.save(task1);
        } else {
            throw new NotFoundException("Task not found");
        }
        return task1;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Long setTaskActivity(Long id, Long activity) {
        Task task = getTask(id);
        activity = task.getActivity() + activity;
        task.setActivity(activity);
        taskRepository.save(task);
        return activity;
    }

    public List<Task> getAllTasksByUserId(Long userId) {
        AllTaskByUserId allTaskByUserId = new AllTaskByUserId();
        List<Project> projects = projectRepository.findByUserId(userId);
        System.out.println(projects);
        List<Task> tasks = new ArrayList<>();
        for (Project project : projects) {
            tasks.addAll(getTasksByProjectId(project.getId()));
        }
        return tasks;
    }

    public AllTaskByUserId getAllTasksByUserIdKanban(Long userId) {
        AllTaskByUserId allTaskByUserId = new AllTaskByUserId();
        List<Project> projects = projectRepository.findByUserId(userId);
        System.out.println(projects);
        List<Task> tasks = new ArrayList<>();
        for (Project project : projects) {
            tasks.addAll(getTasksByProjectId(project.getId()));
        }
        List<Task> todo = tasks.stream().filter(el -> el.getStatus().equals("todo")).collect(Collectors.toList());
        List<Task> inProgress = tasks.stream().filter(el -> el.getStatus().equals("inProgress")).collect(Collectors.toList());
        List<Task> done = tasks.stream().filter(el -> el.getStatus().equals("done")).collect(Collectors.toList());
        allTaskByUserId.setDone(done);
        allTaskByUserId.setInProgress(inProgress);
        allTaskByUserId.setTodo(todo);
        return allTaskByUserId;


    }
}
