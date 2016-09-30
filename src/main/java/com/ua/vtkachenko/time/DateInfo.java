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
}
