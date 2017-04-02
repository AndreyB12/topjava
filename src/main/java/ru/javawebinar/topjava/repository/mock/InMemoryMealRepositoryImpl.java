package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int userId, int id) {
        LOG.info("delete " + id);
        Meal meal = repository.get(id);
        if (meal == null || meal.getUserId() != userId)
            return false;
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        if (meal.getUserId() == userId)
            return meal;
        else return null;
    }


    //  @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return getBetweenDates(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public Collection<Meal> getBetweenDates(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

