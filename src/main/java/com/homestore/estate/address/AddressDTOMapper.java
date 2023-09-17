package com.homestore.estate.address;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class AddressDTOMapper implements Function<Address, AddressDTO> {

    @Override
    public AddressDTO apply(Address address) {
        return new AddressDTO(
                address.getCounty(),
                address.getCity(),
                address.getStreet(),
                address.getNumber()
        );
    }
}