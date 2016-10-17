package com.ua.vtkachenko.time;

import org.omg.CORBA.Object;

import java.time.*;
import java.util.*;

public class WorkCalendar {

    private final int year;
    private Set<DateInfo> wd = new HashSet<DateInfo>();

    public WorkCalendar(int year) {
        if (year > 0){
        this.year = year;
        } else {throw new IllegalArgumentException();};
    }

    public boolean addWeekend(LocalDate date, DayType dt, String descr) {

            return wd.add(new DateInfo(date, dt, descr));
    }

    public boolean deleteWeekend(DateInfo di) {

        return wd.remove(di);
    }

    public DateInfo getInfo(LocalDate date) {

        if (date.getYear() == year)
        {
            for (DateInfo di : wd) {
                if (di.getDate().equals(date))
                    return di;
            }
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY){
                return new DateInfo(date, DayType.WEEKEND, "Weekend");
            } else {
                return new DateInfo(date, DayType.WORKDAY, "Work day");
            }

        } else {throw new IllegalArgumentException(); }
    }

}
