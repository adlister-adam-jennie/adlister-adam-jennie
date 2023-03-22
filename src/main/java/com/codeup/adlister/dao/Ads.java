package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad) throws SQLIntegrityConstraintViolationException;

    List<Ad> userAds(String username);

    void deleteAds(int ad_id);

    void updateAd(long ad_id, String title, String description, List<Category> categories) throws SQLIntegrityConstraintViolationException;

    Ad getAd(long adId);
}
