package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal);

    // void delete(int id);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);


    // Collection<Meal> getAll();

    Collection<Meal> getAll(int userId);

    Collection<Meal> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate);
}
