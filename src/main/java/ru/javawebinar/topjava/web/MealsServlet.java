package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by butkoav on 25.03.2017.
 */
public class MealsServlet extends HttpServlet {
private static final Logger LOG = getLogger(MealsServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to meals");

//        request.getRequestDispatcher("/users.jsp").forward(request, response);
        response.sendRedirect("meals.jsp");
    }
}
