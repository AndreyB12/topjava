package ru.javawebinar.topjava;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.UserService;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final ModelMatcher<Meal> MATCHER = new ModelMatcher<>();
    public static int userId = START_SEQ;
    @Autowired
    private static UserService userService;

    public static void init() {
        userId = userService.getByEmail("user@yandex.ru").getId();

    }
}
