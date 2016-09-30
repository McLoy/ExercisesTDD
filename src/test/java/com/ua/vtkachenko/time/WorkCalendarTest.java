package com.ua.vtkachenko.time;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class WorkCalendarTest {

    @Test
    public void getInfoTest() {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(date, "Independence day");
        DateInfo info = calendar.getInfo(date);
        assertNotNull(info);
    }

    @Test
    public void addWeekendTest() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(date, "Independence day");
        DateInfo info = calendar.getInfo(date);
        assertEquals(date, info.getDate());
        assertEquals(DayType.WEEKEND, info.getType());
        assertEquals("Independence day", info.getDescription());
    }

    @Test
    public void sundayShouldBeWeekend(){
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.OCTOBER, 2);
        DateInfo info = calendar.getInfo(date);
        assertEquals("Weekend", info.getDescription());
        assertEquals(DayType.WEEKEND, info.getType());
    }

    @Test
    public void saturdayShouldBeWeekend(){
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.OCTOBER, 1);
        DateInfo info = calendar.getInfo(date);
        assertEquals("Weekend", info.getDescription());
        assertEquals(DayType.WEEKEND, info.getType());
    }

    @Test
    public void testDateInfoNotNullAlways() throws Exception {
        LocalDate start = LocalDate.of(2016,1,1);
        LocalDate fin = LocalDate.of(2017,1,1);
        WorkCalendar calendar = new WorkCalendar(2016);
        while (start.isBefore(fin)) {
            LocalDate lc = start;
            DateInfo info = calendar.getInfo(lc);
            assertNotNull(info);
            start = start.plusDays(1);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testYearShouldBeGreaterThan0() throws Exception {
        WorkCalendar calendar = new WorkCalendar(-2016);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDateOfAnotherYear() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2014);
        LocalDate date = LocalDate.of(2016, Month.OCTOBER, 1);
        DateInfo info = calendar.getInfo(date);
    }

    @Test
    public void testItIsWorkingDay() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.SEPTEMBER, 30);
        DateInfo info = calendar.getInfo(date);
        assertEquals("Work day", info.getDescription());
    }

    @Test
    public void testTestTypeOfDay() throws Exception {
        LocalDate start = LocalDate.of(2016,1,1);
        LocalDate fin = LocalDate.of(2017,1,1);
        WorkCalendar calendar = new WorkCalendar(2016);
        while (start.isBefore(fin)) {
            LocalDate lc = start;
            DateInfo info = calendar.getInfo(lc);
            if (lc.getDayOfWeek().equals(DayOfWeek.SATURDAY) || lc.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            assertEquals(DayType.WEEKEND, info.getType());
            start = start.plusDays(1);
        }
    }
}
