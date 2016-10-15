package com.ua.vtkachenko.time;

import java.time.LocalDate;
import java.util.Objects;

public class DateInfo {

    private LocalDate date;
    private DayType type;
    private String description;

    public DateInfo(LocalDate date, DayType type, String description) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(type);
        this.date = date;
        this.type = type;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public DayType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int compareTo(Object obj){

        if (!(obj instanceof DateInfo))
            return 1;

        DateInfo di = (DateInfo)obj;
        LocalDate d = di.getDate();

        if (this.date.equals(d))
            return 0;
        else if (this.date.isBefore(d))
            return -1;
        else
            return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateInfo dateInfo = (DateInfo) o;

        return date != null ? date.equals(dateInfo.date) : dateInfo.date == null;

    }

    @Override
    public int hashCode() {
        return date != null ? date.hashCode() : 0;
    }
}
