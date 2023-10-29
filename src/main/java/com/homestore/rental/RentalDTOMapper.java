package com.homestore.rental;

import com.homestore.ad.AdDTOMapper;
import com.homestore.ad.AdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RentalDTOMapper implements Function<Rental, RentalResponse> {

    private final AdDTOMapper adMapper;

    @Override
    public RentalResponse apply(Rental rental) {
        AdResponse ad = adMapper.apply(rental.getAd());

        return new RentalResponse(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getPrice(),
                rental.getPropertyId(),
                ad,
                rental.getTenantId()
        );
    }
}