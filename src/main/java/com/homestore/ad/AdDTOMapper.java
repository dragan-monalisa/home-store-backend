package com.homestore.ad;

import com.homestore.property.PropertyResponse;
import com.homestore.property.PropertyDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AdDTOMapper implements Function<Ad, AdResponse> {

    private final PropertyDTOMapper propertyMapper;

    @Override
    public AdResponse apply(Ad ad) {
        PropertyResponse property = propertyMapper.apply(ad.getProperty());

        return new AdResponse(
                ad.getId(),
                ad.getCreatedAt(),
                ad.getStatus(),
                ad.getTitle(),
                ad.getPrice(),
                ad.getCategory(),
                ad.getRealtorId(),
                property
        );
    }
}