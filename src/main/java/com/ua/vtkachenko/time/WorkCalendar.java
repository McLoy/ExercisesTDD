package com.ua.vtkachenko.time;

import java.time.*;
import java.util.*;

public class WorkCalendar {

    private final int year;
    //private Set<DateInfo> wd = new HashSet<DateInfo>();
    private Map<LocalDate, String> wd = new HashMap<LocalDate, String>();

    public WorkCalendar(int year) {
        if (year > 0) {
            this.year = year;
            LocalDate curr = LocalDate.of(year,1,1);
            while (!curr.equals(LocalDate.of(year+1,1,1))){
                if (curr.getDayOfWeek() == DayOfWeek.SATURDAY || curr.getDayOfWeek() == DayOfWeek.SUNDAY ){
                    wd.put(curr, "Weekend");
                }
                curr = curr.plusDays(1);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean addWeekend(LocalDate date, DayType dt, String descr) {

        if (year == date.getYear()){
            //return wd.add(new DateInfo(date, dt, descr));
            wd.put(date, descr);
        return true;}
        else
            return false;

    }

    public boolean deleteWeekend(LocalDate di) {

        if (wd.get(di) != null){
            wd.remove(di);
            return true;
        } else {
            return false;
        }
        //return wd.remove(di);
    }

    public DateInfo getInfo(LocalDate date) {

//        if (date.getYear() == year){
//            for (DateInfo di : wd) {
//                if (di.getDate().equals(date))
//                    return di;
//            }
//        }
//
//
//        if (date.getDayOfWeek() == DayOfWeek.SUNDAY || date.getDayOfWeek() == DayOfWeek.SATURDAY) {
//            return new DateInfo(date, DayType.WEEKEND, "Weekend");
//        } else {
//            return new DateInfo(date, DayType.WORKDAY, "Work day");
//        }
//
//        } else {throw new IllegalArgumentException(); }

        if(date.getYear() == year){
           String descr =  wd.get(date);
            if (descr != null){
                return new DateInfo(date, DayType.WEEKEND, descr);
            } else {
                return new DateInfo(date, DayType.WORKDAY, "Work day");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getCountOfHolydays() {
        return wd.size();
    }
}
