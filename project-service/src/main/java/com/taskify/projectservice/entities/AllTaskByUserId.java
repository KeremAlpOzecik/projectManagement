package com.taskify.projectservice.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AllTaskByUserId {
    private List<Task> todo;
    private List<Task> inProgress;
    private List<Task> done;

}
