package com.micro.calendar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Calendar {

    private Long id;
    private String description;
    private String status;
    private LocalDate date;
    private Long userId;
    private String color;
}
