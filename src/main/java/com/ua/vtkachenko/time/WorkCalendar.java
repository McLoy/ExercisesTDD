package com.ua.vtkachenko.time;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkCalendar {
    private final int year;
    public WorkCalendar(int year) {
        if (year > 0){
        this.year = year;
        } else {throw new IllegalArgumentException();};
    }

    public void addWeekend(LocalDate date, String description) {

    }

    public DateInfo getInfo(LocalDate date) {

        if (date.getYear() == year)
        {
            String descr = "";
            if (date.equals(LocalDate.of(2016,8,24))) {
                descr = "Independence day";
                return new DateInfo(date, DayType.WEEKEND, descr);
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY){
                descr = "Weekend";
                return new DateInfo(date, DayType.WEEKEND, descr);
            } else {
                descr = "Work day";
                return new DateInfo(date, DayType.WORKDAY, descr);
            }

        } else {throw new IllegalArgumentException(); }
    }
}
