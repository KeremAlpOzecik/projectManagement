package com.tez.todoservice.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoRequest implements Serializable {

    private String content;
}
