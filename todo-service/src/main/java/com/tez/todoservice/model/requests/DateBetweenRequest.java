package com.tez.todoservice.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class DateBetweenRequest {

    private LocalDate start;
    private LocalDate end;
}
