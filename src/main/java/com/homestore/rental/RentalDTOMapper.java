package com.homestore.rental;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class RentalDTOMapper implements Function<Rental, RentalResponse> {

    @Override
    public RentalResponse apply(Rental rental) {
        return new RentalResponse(
                rental.getId(),
                rental.getStartDate(),
                rental.getEndDate(),
                rental.getPrice(),
                rental.getPropertyId(),
                rental.getAdId(),
                rental.getTenantId()
        );
    }
}