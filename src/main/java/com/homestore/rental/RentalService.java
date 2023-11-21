package com.homestore.rental;

import com.homestore.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalService {

    Page<RentalResponse> getRentals(User realtor, Pageable pageable);

    RentalResponse getRental(Long id);

    void saveRental(RentalRequest request);
}
