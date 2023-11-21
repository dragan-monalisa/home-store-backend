package com.homestore.favorite;

import com.homestore.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<Page<FavoriteResponse>> getMyFavorites(@AuthenticationPrincipal User user,
                                                                 Pageable pageable){
        return ResponseEntity.ok(favoriteService.getMyFavorites(user, pageable));
    }

    @PostMapping
    public ResponseEntity<String> saveAdToFavorites(@AuthenticationPrincipal User user,
                                                    Long adId){
        favoriteService.saveAdToFavorites(user, adId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{adId}")
    public ResponseEntity<String> deleteAdFromFavorites(@AuthenticationPrincipal User user,
                                                        @PathVariable Long adId){
        favoriteService.deleteAdFromFavorites(user, adId);

        return ResponseEntity.noContent().build();
    }
}