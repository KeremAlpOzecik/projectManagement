package com.micro.calendar;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RequestMapping("/v1/api/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    @GetMapping()
    public List<Calendar> setCalendars(@RequestParam("userId") Long userId) throws NotFoundException {
        return calendarService.setCalendar(userId);
    }
}
