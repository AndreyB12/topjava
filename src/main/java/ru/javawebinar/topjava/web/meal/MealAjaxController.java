package ru.javawebinar.topjava.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


/**
 * Created by butkoav on 20.05.2017.
 */
@RestController
@RequestMapping(value = MealAjaxController.REST_URL)
public class MealAjaxController extends AbstractMealController {
    static final String REST_URL = "/ajax/profile/meals";


    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping()
    public void createOrUpdate(@RequestParam("id") Integer id,
                               //@RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                               @RequestParam("dateTime") LocalDateTime dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories) {
        Meal meal = new Meal(id, dateTime, description, calories);

        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, AuthorizedUser.id);
        }
    }

    @Override
    @GetMapping("/filter")
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}