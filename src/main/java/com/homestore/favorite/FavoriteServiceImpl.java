package com.homestore.favorite;

import com.homestore.ad.Ad;
import com.homestore.ad.AdService;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService{

    private final FavoriteRepository favoriteRepository;
    private final FavoriteDTOMapper favoriteMapper;
    private final AdService adService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public Page<FavoriteResponse> getMyFavorites(User user, Pageable pageable) {
        Page<Favorite> page = favoriteRepository.findAllByUser(user, pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No ad saved!");
        }

        return page.map(favoriteMapper);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    public void saveAdToFavorites(User user, Long adId) {
        Ad ad = adService.findAdById(adId)
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));

        var favorite = Favorite.builder()
                .user(user)
                .ad(ad)
                .build();

        favoriteRepository.save(favorite);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void deleteAdFromFavorites(User user, Long adId) {
        Favorite favorite = favoriteRepository.findAllByUserAndAd_Id(user, adId)
                .orElseThrow(() -> new ResourceNotFoundException("Ad not found!"));

        favoriteRepository.delete(favorite);
    }
}