package com.homestore.estate.rental;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class RentalDTOMapper implements Function<Rental, RentalDTO> {

    @Override
    public RentalDTO apply(Rental rental) {
        return new RentalDTO(
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
