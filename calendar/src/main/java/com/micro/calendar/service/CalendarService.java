package com.micro.calendar.service;

import com.micro.calendar.controller.ProjectFeignClient;
import com.micro.calendar.controller.TodoFeignClient;
import com.micro.calendar.controller.UserFeignClient;
import com.micro.calendar.entity.Calendar;
import com.micro.calendar.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final UserFeignClient userFeignClient;
    private final TodoFeignClient todoFeignClient;
    private final ProjectFeignClient projectFeignClient;


    public List<Calendar> setCalendar(Long userId) throws NotFoundException {
        List<Calendar> calendars = new ArrayList<>();
        AtomicInteger count = new AtomicInteger();
        User user = userFeignClient.getUserByUserId(userId).getBody().getData();
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        todoFeignClient.getAllByUserId(userId).forEach(todo -> {
            count.getAndIncrement();
            Calendar calendar = new Calendar();
            calendar.setTitle(todo.getContent());
            calendar.setFrom(todo.getDate());
            calendar.setTo(todo.getDate());
            calendar.setColor(generateColorCode());
            calendar.setId(count.get());
            calendars.add(calendar);
        });
        projectFeignClient.getAllTasksByUserId(userId).forEach(project -> {
            count.getAndIncrement();
            Calendar calendar = new Calendar();
            calendar.setTitle(project.getDescription());
            calendar.setFrom(project.getEndDate());
            calendar.setTo(project.getEndDate());
            calendar.setColor(generateColorCode());
            calendar.setId(count.get());
            calendars.add(calendar);
        });


        return calendars;
    }

    private String generateColorCode() {
        return String.format("#%06X", (int) (Math.random() * 0xFFFFFF));
    }


}
