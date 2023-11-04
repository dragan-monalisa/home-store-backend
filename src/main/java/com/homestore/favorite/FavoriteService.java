package com.homestore.favorite;

import com.homestore.security.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService {
    Page<FavoriteResponse> getFavorites(User user, Pageable pageable);

    void saveAd(User user, Long adId);

    void deleteAd(User user, Long adId);
}