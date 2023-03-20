package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.MySQLAdsDao;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateAdServlet", urlPatterns = "/ads/update")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            System.out.println("hey we got here");
            response.sendRedirect("/profile");
            return;
        }
        long adId = Integer.parseInt(request.getParameter("id"));
        Ad ad = DaoFactory.getAdsDao().getAd(adId);
        request.getSession().setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);

//        get is the form
//        post is the submit
//        new method in the dao
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long adId = Integer.parseInt(request.getParameter("id"));
        String updateTitle = request.getParameter("title");
        String updateDescription = request.getParameter("description");
        DaoFactory.getAdsDao().updateAd(adId, updateTitle, updateDescription);

        response.sendRedirect("/profile");
    }

}
