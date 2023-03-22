package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ProfileInfoServlet", urlPatterns = "/ads/information")
public class ProfileInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = Integer.parseInt(request.getParameter("userid"));
        User user = DaoFactory.getUsersDao().findByUserId(userId);
        request.getSession().setAttribute("user", user);

        System.out.println(request.getSession().getAttribute("userId"));
        request.getRequestDispatcher("/WEB-INF/ads/information.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long userId = Integer.parseInt(request.getParameter("userid"));
        String email = request.getParameter("email");
        DaoFactory.getUsersDao().updateEmail(userId, email);
        response.sendRedirect("/profile");

    }
}
