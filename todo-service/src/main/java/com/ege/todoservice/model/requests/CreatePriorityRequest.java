package com.ege.todoservice.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePriorityRequest {

    private Long userId;
    private String priorityName;
}
