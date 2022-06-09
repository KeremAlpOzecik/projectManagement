package com.ege.todoservice.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TodoDto {

    private Long id;
    private String content;
    private Boolean completed;
    private Long userId;
    private LocalDate date;
}

