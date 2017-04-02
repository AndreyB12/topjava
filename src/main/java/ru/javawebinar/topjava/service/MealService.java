package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface MealService {

    Meal save(Meal meal);

    void update(Meal meal);

    void delete(int userid, int id) throws NotFoundException;

    Meal get(int userId, int id) throws NotFoundException;

    List<Meal> getAll(int userId);

    List<Meal> getByDates(int userId, LocalDate startDate, LocalDate endDate);


}