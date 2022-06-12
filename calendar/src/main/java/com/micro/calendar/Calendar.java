package com.micro.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    private int id;
    private String description;
    private String status;
    private LocalDate date;
    private Long userId;
    private String color;
}
