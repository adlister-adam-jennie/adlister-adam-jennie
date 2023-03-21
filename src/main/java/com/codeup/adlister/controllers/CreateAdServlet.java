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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String service = request.getParameter("category_1");
        String product = request.getParameter("category_2");
        String recruitment = request.getParameter("category_3");
        String limitedTime = request.getParameter("category_4");

        List<Category> categories = new ArrayList<>();

        if(service != null){
            categories.add(new Category(1, service));
        }
        if(product != null){
            categories.add(new Category(2, product));
        }
        if(recruitment != null){
            categories.add(new Category(3, recruitment));
        }
        if(limitedTime != null){
            categories.add(new Category(4, limitedTime));
        }

        long userId = ((User) request.getSession().getAttribute("user")).getId();

        Ad ad = new Ad(
                userId, // for now we'll hardcode the user id
                request.getParameter("title"),
                request.getParameter("description"),
                categories
        );

        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/profile");
    }
}
