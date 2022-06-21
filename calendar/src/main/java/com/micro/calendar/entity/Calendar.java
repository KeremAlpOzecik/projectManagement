package com.micro.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    private int id;
    private String title;
    private LocalDate from;
    private LocalDate to;
    private String color;
}
