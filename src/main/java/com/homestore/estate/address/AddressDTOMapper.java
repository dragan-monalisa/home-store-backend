package com.homestore.estate.address;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class AddressDTOMapper implements Function<Address, AddressResponse> {

    @Override
    public AddressResponse apply(Address address) {
        return new AddressResponse(
                address.getCounty(),
                address.getCity(),
                address.getStreet(),
                address.getNumber()
        );
    }
}