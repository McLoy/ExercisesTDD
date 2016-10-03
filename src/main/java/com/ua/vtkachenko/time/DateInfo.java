package com.ua.vtkachenko.time;

import java.time.LocalDate;
import java.util.Objects;

public class DateInfo implements Comparable{
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)date.toEpochDay();
        result = prime * result + description.length();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateInfo other = (DateInfo) obj;
        if (this.type != type)
            return false;
        if (this.date != date)
            return false;
        if (this.description != description)
            return false;
        return true;
    }
}
