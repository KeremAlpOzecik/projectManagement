package com.taskify.projectservice.entities.event;

import com.taskify.projectservice.entities.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectTasks {
    private List<Task> todo;
    private List<Task> inProgress;
    private List<Task> done;
}
