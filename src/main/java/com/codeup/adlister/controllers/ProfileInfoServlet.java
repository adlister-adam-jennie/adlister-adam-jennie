package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
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
        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/profile");
            return;
        }
        String username = ((User) request.getSession().getAttribute("user")).getUsername();
        String email = ((User) request.getSession().getAttribute("user")).getEmail();

        request.setAttribute("user", DaoFactory.getUsersDao().findByUsername(username));
        request.setAttribute("user", DaoFactory.getUsersDao().findByUserEmail(email));

        request.getRequestDispatcher("/WEB-INF/ads/information.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
