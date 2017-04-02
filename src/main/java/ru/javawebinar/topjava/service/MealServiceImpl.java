package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) throws NotFoundException {
        LOG.info(String.format("Service, save, Meal=%s", meal));
        return repository.save(meal);
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        LOG.info(String.format("Service, delete, id=%s", id));

        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        LOG.info(String.format("Service, get, id=%s", id));
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        LOG.info(String.format("Service, getAll, userId=%s", userId));
        return new ArrayList<>(repository.getAll(userId));
    }

    @Override
    public List<Meal> getByDates(int userId, LocalDate startDate, LocalDate endDate) {
        LOG.info(String.format("Service, getByDates, userId=%s, startDate=%s, endDate=%s.", userId, startDate, endDate));
        return new ArrayList<>(repository.getBetweenDates(userId, startDate, endDate));
    }


}