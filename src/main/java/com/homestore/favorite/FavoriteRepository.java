package com.homestore.favorite;

import com.homestore.security.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Page<Favorite> findAllByUser(User user, Pageable pageable);

    Optional<Favorite> findAllByUserAndAd_Id(User user, Long adId);
}