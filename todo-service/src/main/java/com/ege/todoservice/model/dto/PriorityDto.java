package com.ege.todoservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriorityDto {

    private Long id;
    private String priorityName;
    private Boolean priorityStatus;
    private Long userId;
}
