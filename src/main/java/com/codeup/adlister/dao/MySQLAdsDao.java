package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ad_lister_ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }


    @Override
    public List<Ad> userAds(String username) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ad_lister_ads as a INNER JOIN ad_lister_users as u on a.user_id = u.user_id WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the users ads.", e);
        }
    }

    @Override
    public void deleteAds(int ad_id) {
        try {
            PreparedStatement deleteCategories = connection.prepareStatement("DELETE FROM ad_lister_category_ad WHERE ad_id = ?;");
            PreparedStatement deleteAd = connection.prepareStatement("DELETE FROM ad_lister_ads WHERE ad_id = ?");
            deleteCategories.setInt(1, ad_id);
            deleteCategories.executeUpdate();
            deleteAd.setInt(1, ad_id);
            deleteAd.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting ad.", e);
        }
    }

    @Override
    public void updateAd(long ad_id, String title, String description, List<Category> categories) {
//        needs to update the categories as well
        try {
            String insertQuery = "UPDATE ad_lister_ads SET title = ?, description = ? WHERE ad_id = ?;";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            PreparedStatement stmt2 = connection.prepareStatement("DELETE FROM ad_lister_category_ad WHERE ad_id = ?;");
            PreparedStatement stmt3 = connection.prepareStatement("INSERT INTO ad_lister_category_ad(ad_id, category_id) VALUES (?,?)");
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, ad_id);
            stmt.executeUpdate();
            stmt2.setLong(1,ad_id);
            stmt2.executeUpdate();
            if (categories != null) {
                for (Category category : categories) {
                    stmt3.setLong(1, ad_id);
                    stmt3.setLong(2, category.getId());
                    stmt3.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the ad.", e);
        }
    }

    @Override
    public Ad getAd(long adId) {
//        needs to grab categories as well
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ad_lister_ads WHERE ad_id = ?");
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return extractAd(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving single ad.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
//            add categories into the insert
            String insertQuery = "INSERT INTO ad_lister_ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            long adId = rs.getLong(1);
            PreparedStatement stmt2 = connection.prepareStatement("INSERT INTO ad_lister_category_ad(ad_id, category_id) VALUES (?,?)");
//            iterate over the ad categories and insert the ad_category (datebase the ids)
            for (Category category: ad.getCategories()) {
                stmt2.setLong(1, adId);
                System.out.println(ad.getId());
                stmt2.setLong(2, category.getId());
                System.out.println(category.getId());
                stmt2.executeUpdate();
            }
            return adId;
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }


    private Ad extractAd(ResultSet rs) throws SQLException {
//        long adId = rs.getLong();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM ad_lister_category_ad as a INNER JOIN ad_lister_categories as c on a.category_id = c.category_id WHERE a.ad_id = ? ");
//        List<Category> categories = fetchCategoriesById(rs.getLong("ad_id"));
        stmt.setLong(1, rs.getLong("ad_id"));
        List<Category> categories = createCategoriesFromResults(stmt.executeQuery());
        Ad ad = new Ad(
            rs.getLong("ad_id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")

        );
        ad.setCategories(categories);
        return ad;

    }

    private List<Category> fetchCategoriesById(long adId){
        List<Category> name = new ArrayList<>();
        name.add(new Category(1, "service"));
        name.add(new Category(2, "product"));
        name.add(new Category(3, "recruitment"));
        name.add(new Category(4, "limitedTime"));
        return name;
    }

    private List<Category> createCategoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(new Category(rs.getLong("category_id") ,rs.getString("category")));
        }
        return categories;
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }


}
