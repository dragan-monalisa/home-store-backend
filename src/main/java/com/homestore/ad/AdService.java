package com.homestore.ad;

import com.homestore.security.user.User;
import java.util.List;
import java.util.Optional;

public interface AdService {
    List<AdResponse> getAds();
    AdResponse getAd(Long id);
    List<AdResponse> getAdsByFilters(SearchCriteria searchCriteria);

    List<AdResponse> getMyAds(User user);

    List<AdResponse> getMyAdsByStatus(User user, StatusEnum status);

    void deleteAd(User user, Long id);

    void updateAd(User user, Long id, AdRequest request);

    Optional<Ad> findAdById(Long id);

    void saveAd(User user, AdRequest request);
}