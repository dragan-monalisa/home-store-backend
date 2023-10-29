package com.homestore.rental;

import com.homestore.security.user.User;
import java.util.List;

public interface RentalService {

    List<RentalResponse> getRentals(User realtor);

    RentalResponse getRental(Long id);

    void saveRental(RentalRequest request);
}
