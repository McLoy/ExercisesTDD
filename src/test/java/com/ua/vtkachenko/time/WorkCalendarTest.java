package com.ua.vtkachenko.time;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class WorkCalendarTest {

    private WorkCalendar calendar2016;

    @Before
    public void setUp() throws Exception {
        calendar2016 = new WorkCalendar(2016);
    }

    @Test
    public void getInfoTest() {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(date, DayType.WEEKEND, "Independence day");
        DateInfo info = calendar.getInfo(date);
        assertNotNull(info);
    }

    @Test
    public void addWeekend() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate date = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(date, DayType.WEEKEND, "Independence day");
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
            DateInfo info = calendar.getInfo(start);
            if (start.getDayOfWeek().equals(DayOfWeek.SATURDAY) || start.getDayOfWeek().equals(DayOfWeek.SUNDAY))
            assertEquals(DayType.WEEKEND, info.getType());
            start = start.plusDays(1);
        }
    }

    @Test
    public void testAddSeveralWeekendDays() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate weekday1 = LocalDate.of(2016, Month.FEBRUARY, 14);
        LocalDate weekday2 = LocalDate.of(2016, Month.DECEMBER, 31);
        LocalDate weekday3 = LocalDate.of(2016, Month.AUGUST, 24);
        assertEquals(true, calendar.addWeekend(weekday1, DayType.WEEKEND, "Happy Valentines Day"));
        assertEquals(true, calendar.addWeekend(weekday2, DayType.WEEKEND, "Happy New Year"));
        assertEquals(true, calendar.addWeekend(weekday3, DayType.WEEKEND, "Independence day of Ukraine"));
    }

    @Test
    public void testAddWeekendsDoubles() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate weekday1 = LocalDate.of(2016, Month.FEBRUARY, 14);
        assertEquals(true, calendar.addWeekend(weekday1, DayType.WEEKEND, "Happy Valentines Day"));
    }

    @Test
    public void testDeleteWeekend() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate weekday1 = LocalDate.of(2016, Month.FEBRUARY, 14);
        LocalDate weekday2 = LocalDate.of(2016, Month.DECEMBER, 31);
        LocalDate weekday3 = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(weekday1, DayType.WEEKEND, "Happy Valentines Day");
        calendar.addWeekend(weekday2, DayType.WEEKEND, "Happy New Year");
        calendar.addWeekend(weekday3, DayType.WEEKEND, "Independence day of Ukraine");
        LocalDate fordel = LocalDate.of(2016, Month.AUGUST, 24); //Independence day of Ukraine
        DateInfo di = calendar.getInfo(fordel);
        assertEquals(true,calendar.deleteWeekend(fordel));
    }

    @Test
    public void testFindWeekdayInEmptyStorage() throws Exception {
        WorkCalendar cal = new WorkCalendar(2016);
        LocalDate d = LocalDate.of(2016, Month.AUGUST, 24);
        DateInfo info = cal.getInfo(d);
        assertEquals(DayType.WORKDAY, info.getType());
    }

    @Test
    public void testReturnWeekdayFromCalendar() throws Exception {
        WorkCalendar cal = new WorkCalendar(2016);
        LocalDate di = LocalDate.of(2016, Month.OCTOBER, 17);
        DateInfo i = cal.getInfo(di);
        assertEquals(DayType.WORKDAY, i.getType());
    }

    @Test
    public void testAddedWeekdayDayType() throws Exception {
        WorkCalendar cal = new WorkCalendar(2016);
        LocalDate d = LocalDate.of(2016, Month.AUGUST, 24);
        cal.addWeekend(d, DayType.WEEKEND, "Independence day");
        assertEquals(DayType.WEEKEND, cal.getInfo(d).getType());
    }

    @Test
    public void testAddAndDeleteWeekday() throws Exception {
        WorkCalendar cal = new WorkCalendar(2016);
        LocalDate d = LocalDate.of(2016, Month.AUGUST, 24);
        cal.addWeekend(d, DayType.WEEKEND, "Independence day");
        assertEquals(true, cal.deleteWeekend(d));
    }

    @Test
    public void testDelWeekDayFromEmptyCAlendar() throws Exception {
        WorkCalendar wc = new WorkCalendar(2016);
        LocalDate d = LocalDate.of(2016, Month.AUGUST, 24);
        assertEquals(false, wc.deleteWeekend(d));
    }

    @Test
    public void testAddWrongFormatDateInCalendar() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate weekday1 = LocalDate.of(2016, Month.FEBRUARY, 14);
        LocalDate weekday2 = LocalDate.of(2016, Month.DECEMBER, 31);
        LocalDate weekday3 = LocalDate.of(2016, Month.AUGUST, 24);
        calendar.addWeekend(weekday1, DayType.WEEKEND, "Happy Valentines Day");
        calendar.addWeekend(weekday2, DayType.WEEKEND, "Happy New Year");
        calendar.addWeekend(weekday3, DayType.WEEKEND, "Independence day of Ukraine");
        assertEquals(106, calendar.getCountOfHolydays());
    }

    @Test
    public void testAddWeekdayFromWrongYear() throws Exception {
        WorkCalendar calendar = new WorkCalendar(2016);
        LocalDate weekday1 = LocalDate.of(2015, Month.FEBRUARY, 14);
        assertEquals(false, calendar.addWeekend(weekday1, DayType.WEEKEND, "Happy Valentines Day"));
    }

    @Test
    public void getAmountOfWeekdays() throws Exception {
        Assertions.assertThat(calendar2016.getCountOfHolydays()).isGreaterThan(0);
    }

    @Test
    public void isWeekend() throws Exception {
        LocalDate weekday3 = LocalDate.of(2016, Month.AUGUST, 24);
        calendar2016.addWeekend(weekday3, DayType.WEEKEND, "Independence day of Ukraine");
        Assertions.assertThat(calendar2016.isWeekend(weekday3)).isTrue();
        Assertions.assertThat(calendar2016.isWeekend(LocalDate.of(2016, 8, 25))).isFalse();
    }

    @Test
    public void setWorkDay() throws Exception {
        LocalDate day = LocalDate.of(2016,11,5);
        calendar2016.setWorkDay(day);
        Assertions.assertThat(calendar2016.getInfo(day).getType()).isEqualTo(DayType.WORKDAY);
        Assertions.assertThat(calendar2016.getDescription(day)).isEqualTo("Work day");
        Assertions.assertThat(calendar2016.getDescription(LocalDate.of(2016,11,4))).isEqualTo("Work day");
    }
}
