package com.micro.calendar;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
@RestController
@RequestMapping("/v1/api/calendar/{userId}")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    @GetMapping()
    public List<Calendar> setCalendars(@PathVariable("userId") Long userId) throws NotFoundException {
        return calendarService.setCalendar(userId);
    }
}
