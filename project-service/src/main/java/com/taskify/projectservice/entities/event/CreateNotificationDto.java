package com.taskify.projectservice.entities.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class CreateNotificationDto {
    private String email;
    private String project;
    private Date date;
}
