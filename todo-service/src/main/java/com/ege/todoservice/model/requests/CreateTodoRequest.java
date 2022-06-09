package com.ege.todoservice.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CreateTodoRequest implements Serializable {

    private Long userId;
    private String content;

}
