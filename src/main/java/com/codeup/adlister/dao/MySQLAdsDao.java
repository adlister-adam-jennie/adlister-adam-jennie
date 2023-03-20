package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("DELETE FROM ad_lister_ads WHERE ad_id = ?");
            stmt.setInt(1, ad_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving from delete ads method.", e);
        }
    }

    @Override
    public void updateAd(long ad_id, String title, String description) {
        try {
            String insertQuery = "UPDATE ad_lister_ads SET title = ?, description = ? WHERE ad_id = ?";
            PreparedStatement stmt = connection.prepareStatement(insertQuery);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, ad_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating the ad.", e);
        }
    }

    @Override
    public Ad getAd(long adId) {
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
            String insertQuery = "INSERT INTO ad_lister_ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }



    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("ad_id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
}
