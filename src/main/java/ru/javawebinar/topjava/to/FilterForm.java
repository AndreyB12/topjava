package ru.javawebinar.topjava.to;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by butkoav on 18.05.2017.
 */
public class FilterForm {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalTime endTime;
}
