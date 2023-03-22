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
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.UpdateAdServlet", urlPatterns = "/ads/update")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/profile");
            return;
        }
        long adId = Integer.parseInt(request.getParameter("id"));
        System.out.println(adId);
//        long categoryId = request.getParameter()
//        System.out.println(categoryId);
//        the get ad method needs to grab the categories as well
        Ad ad = DaoFactory.getAdsDao().getAd(adId);
        List<Category> categories = ad.getCategories();
        for (Category category : categories) {
            request.setAttribute("category" + category.getId(), "checked");
        }
        request.setAttribute("ad", ad);
        request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long adId = Integer.parseInt(request.getParameter("id"));
        List<Category> categories;
        try {
            categories = createCategoryListFromParameters(request.getParameterValues("category"));
        } catch (NullPointerException e) {
            categories = null;
        }
        String updateTitle = request.getParameter("title");
        String updateDescription = request.getParameter("description");
//        needs to update the categories as well
        try {
            DaoFactory.getAdsDao().updateAd(adId, updateTitle, updateDescription, categories);
        } catch (SQLIntegrityConstraintViolationException e) {
                request.setAttribute("error", true);
                Ad ad = new Ad();
                ad.setId(adId);
                ad.setTitle(updateTitle);
                ad.setDescription(updateDescription);
                ad.setCategories(categories);
                request.setAttribute("ad", ad);
                if (categories != null) {
                    for (Category category : categories) {
                        request.setAttribute("category" + category.getId(), "checked");
                    }
                }
                request.getRequestDispatcher("/WEB-INF/ads/update.jsp")
                        .forward(request, response);
        }

        response.sendRedirect("/profile");
    }

    private static List<Category> createCategoryListFromParameters(String[] categoryIds) {
        List<Category> categories = new ArrayList<>();
        for (String categoryId : categoryIds) {
            categories.add(new Category(Integer.parseInt(categoryId)));
        }
        return categories;
    }

}
