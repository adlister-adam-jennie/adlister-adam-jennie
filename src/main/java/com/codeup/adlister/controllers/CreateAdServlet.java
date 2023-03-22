package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                    .forward(request, response);
        } else {
            request.getRequestDispatcher("/login")
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> categories = new ArrayList<>();
        String[] params = request.getParameterValues("category");
        if (params != null) {
            for (String param : params) {
                categories.add(new Category(Integer.parseInt(param)));
            }
        }

        long userId = ((User) request.getSession().getAttribute("user")).getId();

        Ad ad = new Ad(
                userId,
                request.getParameter("title"),
                request.getParameter("description"),
                categories
        );
        try {
            DaoFactory.getAdsDao().insert(ad);
        } catch (SQLIntegrityConstraintViolationException e) {
            request.setAttribute("error", true);
            request.setAttribute("ad", ad);
            for (Category category : categories) {
                request.setAttribute("category" + category.getId(), "checked");
            }
            request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                    .forward(request, response);
        }
        response.sendRedirect("/profile");
    }
}
