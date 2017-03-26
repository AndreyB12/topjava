package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by butkoav on 26.03.2017.
 */
public interface MealsStorage {

    List<Meal> getAll();

    Meal getById(long id);

    void add(LocalDateTime dateTime, String description, int calories);

    void removeById(long id);

    void edit(long id, LocalDateTime dateTime, String description, int calories);
}
