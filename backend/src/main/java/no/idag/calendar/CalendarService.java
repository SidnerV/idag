package no.idag.calendar;

import org.springframework.stereotype.Service;

@Service
public class CalendarService {
    private final CalendarRepository calendarRepo;
    public CalendarService(CalendarRepository calendarRepository) {
        this.calendarRepo = calendarRepository;
    }

    public Calendar createCalendar(String name) {
        return this.calendarRepo.save(new Calendar(name));
    }
}
