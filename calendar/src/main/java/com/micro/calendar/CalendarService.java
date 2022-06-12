package com.micro.calendar;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final UserFeignClient userFeignClient;
    private final TodoFeignClient todoFeignClient;
    private final ProjectFeignClient projectFeignClient;


    public List<Calendar> setCalendar(Long userId) throws NotFoundException {
        List<Calendar> calendars = new ArrayList<>();
        User user = userFeignClient.getUserByUserId(userId).getBody().getData();
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        todoFeignClient.getAllByUserId(userId).getBody().getData().forEach(todo -> {
            Calendar calendar = new Calendar();
            calendar.setDescription(todo.getContent());
            calendar.setStatus(null);
            calendar.setDate(todo.getDate());
            calendar.setUserId(userId);
            calendar.setColor(generateColorCode());
            calendars.add(calendar);
        });
        projectFeignClient.getAllTasksByUserId(userId).getBody().getData().forEach(project -> {
            Calendar calendar = new Calendar();
            calendar.setDescription(project.getName());
            calendar.setStatus(project.getStatus());
            calendar.setDate(project.getEndDate());
            calendar.setUserId(userId);
            calendar.setColor(generateColorCode());
            calendars.add(calendar);
        });


        return calendars;
    }

    private String generateColorCode() {
        return String.format("#%06X", (int) (Math.random() * 0xFFFFFF));
    }


}
