package com.homestore.favorite;

import com.homestore.ad.AdDTOMapper;
import com.homestore.ad.AdResponse;
import com.homestore.security.user.UserDTOMapper;
import com.homestore.security.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class FavoriteDTOMapper implements Function<Favorite, FavoriteResponse> {

    private final UserDTOMapper userMapper;
    private final AdDTOMapper adMapper;

    @Override
    public FavoriteResponse apply(Favorite favorite) {
        UserResponse user = userMapper.apply(favorite.getUser());
        AdResponse ad = adMapper.apply(favorite.getAd());

        return new FavoriteResponse(
                user,
                ad,
                favorite.isAdded()
        );
    }
}