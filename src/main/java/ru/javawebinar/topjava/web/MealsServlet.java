package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.storage.MealsStorage;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.StorageUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by butkoav on 25.03.2017.
 */
public class MealsServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealsServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MealsStorage storage = StorageUtils.getStorage();
        String action = request.getParameter("action");
        if (action != null)
            switch (action.toString()) {
                case "delete":
                    try {
                        long id = Long.parseLong(request.getParameter("id"));
                        storage.removeById(id);
                    } catch (Exception e) {
                    }
                    break;
                case "add":
                    request.getRequestDispatcher("/editmeals.jsp").forward(request, response);
                    break;
                case "edit":
                    long id = Long.parseLong(request.getParameter("id"));
                    Meal meal = storage.getById(id);
                    request.setAttribute("meal", meal);
                    request.getRequestDispatcher("/editmeals.jsp").forward(request, response);
                    break;
            }

        List<MealWithExceed> meals = MealsUtil.getFilteredWithExceeded(storage.getAll(), LocalTime.MIN, LocalTime.MAX, 2000);
        LOG.debug("redirect to meals");
        request.setAttribute("listMeals", meals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        MealsStorage storage = StorageUtils.getStorage();
        String action = request.getParameter("action");
        if (action != null)
            switch (action.toString()) {
                case "add":
                    try {
                        long id = Long.parseLong(request.getParameter("id"));
                        String dscr = request.getParameter("description");
                        int calories = Integer.parseInt(request.getParameter("calories"));
                        String dt = request.getParameter("datetime");
                        LocalDateTime dateTime = LocalDateTime.parse(dt,DateTimeFormatter.ISO_DATE_TIME);
                        storage.add(dateTime,dscr,calories);
                    } catch (Exception e) {
                    }
                    break;
                case "edit":
                    try {
                        long id = Long.parseLong(request.getParameter("id"));
                        String dscr = request.getParameter("description");
                        int calories = Integer.parseInt(request.getParameter("calories"));
                        String dt = request.getParameter("datetime");
                        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("datetime"),DateTimeFormatter.ISO_DATE_TIME);
                        storage.edit(id,dateTime,dscr,calories);
                    } catch (Exception e) {
                    }
                    break;
            }
            response.sendRedirect("meals");
    }

}
