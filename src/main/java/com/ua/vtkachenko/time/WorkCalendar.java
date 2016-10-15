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

//            if (wd.contains(date)){
//                return new DateInfo((wd.))
//            }

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
