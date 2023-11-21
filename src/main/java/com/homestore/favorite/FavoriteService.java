package com.homestore.favorite;

import com.homestore.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteService {
    Page<FavoriteResponse> getMyFavorites(User user, Pageable pageable);

    void saveAdToFavorites(User user, Long adId);

    void deleteAdFromFavorites(User user, Long adId);
}