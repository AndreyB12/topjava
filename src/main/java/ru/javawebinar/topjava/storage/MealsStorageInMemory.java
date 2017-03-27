package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by butkoav on 26.03.2017.
 */
public class MealsStorageInMemory implements MealsStorage {
    private final Map<Long, Meal> meals = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong(0l);

    public MealsStorageInMemory() {
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(lastId.incrementAndGet(), new Meal(lastId.get(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void add(LocalDateTime dateTime, String description, int calories) {

        edit(0l, dateTime, description, calories);
    }

    @Override
    public void removeById(long id) {
        meals.remove(id);
    }

    @Override
    public void edit(long id, LocalDateTime dateTime, String description, int calories) {
        if (id == 0l)
            id = lastId.incrementAndGet();
            meals.put(id, new Meal(id, dateTime, description, calories));
    }

    @Override
    public Meal getById(long id) {
        return meals.get(id);
    }
}
