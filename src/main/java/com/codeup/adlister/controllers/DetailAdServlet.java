package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.DetailAdServlet", urlPatterns = "/ads/detail")
public class DetailAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = request.getParameter("categories");
        Ad ad = DaoFactory.getAdsDao().getAd(adId);
        request.getSession().setAttribute("ad", ad);

        System.out.println(request.getSession().getAttribute("userId"));
        long userId = Integer.parseInt(request.getParameter("userId"));
        User user = DaoFactory.getUsersDao().findByUserId(userId);
        request.getSession().setAttribute("user", user);



//        need to grab the categories as well

        request.getRequestDispatcher("/WEB-INF/ads/detail.jsp").forward(request, response);

    }

}