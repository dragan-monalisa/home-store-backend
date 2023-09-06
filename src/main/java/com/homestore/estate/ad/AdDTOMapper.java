package com.homestore.estate.ad;

import com.homestore.estate.ad.category.CategoryDTO;
import com.homestore.estate.ad.category.CategoryDTOMapper;
import com.homestore.estate.property.PropertyDTO;
import com.homestore.estate.property.PropertyDTOMapper;
import com.homestore.security.user.UserDTO;
import com.homestore.security.user.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdDTOMapper implements Function<Ad, AdDTO> {

    private final UserDTOMapper userMapper;
    private final PropertyDTOMapper propertyMapper;
    private final CategoryDTOMapper categoryMapper;

    @Override
    public AdDTO apply(Ad ad) {
        UserDTO user = userMapper.apply(ad.getUser());
        PropertyDTO property = propertyMapper.apply(ad.getProperty());
        CategoryDTO category = categoryMapper.apply(ad.getCategory());

        return new AdDTO(
                ad.getId(),
                ad.getRealtorId(),
                ad.getPrice(),
                ad.getStatus(),
                ad.getTitle(),
                ad.getCreatedAt(),
                ad.getExpiresAt(),
                user,
                property,
                category
        );
    }
}
