package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> userAds(String username);

    void deleteAds(int ad_id);

    void updateAd(long ad_id, String title, String description, List<Category> categories);

    Ad getAd(long adId);
}
