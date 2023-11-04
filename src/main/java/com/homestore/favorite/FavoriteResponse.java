package com.homestore.favorite;

import com.homestore.ad.AdResponse;
import com.homestore.security.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteResponse {
    private UserResponse user;
    private AdResponse ad;
    private boolean isAdded;
}