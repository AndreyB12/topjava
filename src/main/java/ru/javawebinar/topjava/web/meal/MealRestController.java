package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.AuthorizationError;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;
    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);


    public List<MealWithExceed> getByDatesAndTimes(String startDate, String endDate, String startTime, String endTime) {
        LocalDate sLD = startDate == null ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate eLD = endDate == null ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime sLT = startTime == null ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime eLT = endTime == null ? LocalTime.MAX : LocalTime.parse(endTime);


        return MealsUtil.getFilteredWithExceeded(
                service.getByDates(AuthorizedUser.id(), sLD, eLD)
                , sLT, eLT, AuthorizedUser.getCaloriesPerDay());
    }

    public void delete(int id) {
        Meal meal = service.get(id);
        if (meal.getUserId() == AuthorizedUser.id())
            service.delete(id);
    }

    public void save(String id, String dateTime, String description, String calories) {
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(dateTime),
                description,
                Integer.valueOf(calories), AuthorizedUser.id());
        LOG.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        if (!meal.isNew() && service.get(meal.getId()).getUserId() != meal.getUserId())
            throw new AuthorizationError("Attempt to edit foreign Meal, meal=" + meal + ", userId=" + AuthorizedUser.id());
        service.save(meal);

    }

    public Meal get(int id) {
        Meal meal = service.get(id);
        if (meal.getUserId() != AuthorizedUser.id())
            throw new AuthorizationError("Attempt to get foreign Meal, meal=" + meal + ", userId=" + AuthorizedUser.id());
        return meal;
    }

    public Meal getNew() {
        return new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000, AuthorizedUser.id());
    }
}