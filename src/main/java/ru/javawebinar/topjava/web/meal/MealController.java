package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Created by butkoav on 15.05.2017.
 */
@Controller
@RequestMapping(value = "/meals")
public class MealController {
    @Autowired
    MealRestController mealRestController;
    @RequestMapping( method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id, HttpServletRequest request) {
        mealRestController.delete(id);
        return "redirect:/meals";
    }



    @RequestMapping(value = "/meal", method = RequestMethod.GET)
    public String get(HttpServletRequest request, Model model) {
        String paramId = request.getParameter("id");
        Meal meal = paramId == null ? new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)
                , "", 1000) : mealRestController.get(getId(request));
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request) throws UnsupportedEncodingException {
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

    @RequestMapping(value = "filter" , method = RequestMethod.GET)
    public String filter(HttpServletRequest request){
        return "redirect:/meals";
    }
    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
