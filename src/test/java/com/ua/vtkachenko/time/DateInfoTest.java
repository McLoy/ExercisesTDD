package com.ua.vtkachenko.time;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DateInfoTest {

    public static final LocalDate NOW = LocalDate.now();
    public static final DayType WEEKEND = DayType.WEEKEND;
    public static final String DESCRIPTION = "description";

    @Test(expected = NullPointerException.class)
    public void dateSouldBeNotNull() throws Exception {
        DateInfo dateInfo = new DateInfo(null, DayType.WEEKEND, "description");
    }

   @Test(expected = NullPointerException.class)
    public void typeSouldBeNotNull() throws Exception {
        DateInfo dateInfo = new DateInfo(LocalDate.now(), null, "description");
    }

    @Test
    public void testGetDate() throws Exception {
        DateInfo dateInfo = new DateInfo(NOW, WEEKEND, DESCRIPTION);
        assertEquals(NOW, dateInfo.getDate());
    }

    @Test
    public void testGetType() throws Exception {
        DateInfo dateInfo = new DateInfo(NOW, WEEKEND, DESCRIPTION);
        assertEquals(WEEKEND, dateInfo.getType());
    }

    @Test
    public void testGetDescription() throws Exception {
        DateInfo dateInfo = new DateInfo(NOW, WEEKEND, DESCRIPTION);
        assertEquals(DESCRIPTION, dateInfo.getDescription());
    }
}