package com.homestore.ad;

import com.homestore.security.user.User;
import java.util.List;

public interface AdService {
    List<AdResponse> getAds();
    AdResponse getAd(Long id);
    List<AdResponse> getAdsByCriteria(AdSearchCriteria adSearchCriteria);

    List<AdResponse> getMyAds(User user);

    List<AdResponse> getMyAdsByStatus(User user, StatusEnum status);

    void deleteAd(User user, Long id);

    void updateAd(User user, Long id, AdRequest request);
}