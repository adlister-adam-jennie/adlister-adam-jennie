package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> userAds(String username);

    void deleteAds(int ad_id);

    void updateAd(long ad_id, String title, String description);

    Ad getAd(long adId);
}
