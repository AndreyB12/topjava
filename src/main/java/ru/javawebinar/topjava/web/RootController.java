package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Controller
public class RootController {
    @Autowired
    private UserService userService;


    @Autowired
    MealRestController mealRestController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        AuthorizedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String getMeals(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/meals/delete/{id}", method = RequestMethod.GET)
    public String deleteMeal(@PathVariable int id, HttpServletRequest request) {
        mealRestController.delete(id);
        return "redirect:/meals";
    }


//    @RequestMapping(value = "/meal/{id}", method = RequestMethod.GET)
//    public String getMeal(@PathVariable int id, HttpServletRequest request, Model model) {
//        Meal meal = id == 0 ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
//                , "", 1000) :
//                mealRestController.get(id);
//        model.addAttribute("meal", meal);
//        return "meal";
//    }

    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    public String getMeal(HttpServletRequest request, Model model) {
        String paramId = request.getParameter("id");
        Meal meal = paramId == null ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                , "", 1000) : mealRestController.get(getId(request));
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meal/save", method = RequestMethod.POST)
    public String putMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

        if (request.getParameter("id").isEmpty()) {
            mealRestController.create(meal);
        } else {
            mealRestController.update(meal, getId(request));
        }
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
