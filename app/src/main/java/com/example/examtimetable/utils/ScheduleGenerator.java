package com.example.examtimetable.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ScheduleGenerator {
    public List<String> generateSchedule() {
        List<String> schedule = new ArrayList<>();
        List<String> subjects = Arrays.asList("Math", "Science", "History", "English");
        List<String> dates = Arrays.asList("2024-06-20", "2024-06-21", "2024-06-22", "2024-06-23");
        List<String> times = Arrays.asList("10:00", "13:00", "15:00", "11:00");
        for (int i = 0; i < subjects.size(); i++) {
            String examDetails = subjects.get(i) + " - " + dates.get(i) + " " + times.get(i);
            schedule.add(examDetails);
        }
        return schedule;
    }
}