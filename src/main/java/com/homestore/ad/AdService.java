package com.homestore.ad;

import com.homestore.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AdService {
    Page<AdResponse> getAds(Pageable pageable);
    AdResponse getAd(Long id);
    Page<AdResponse> getAdsByFilters(SearchCriteria searchCriteria, Pageable pageable);

    Page<AdResponse> getMyAdsByStatus(User user, StatusEnum status, Pageable pageable);

    void deleteAd(User user, Long id);

    void updateAd(User user, Long id, AdRequest request);

    void saveAd(User user, AdRequest request);

    void save(Ad ad);

    Optional<Ad> findAdById(Long id);
}