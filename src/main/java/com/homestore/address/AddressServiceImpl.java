package com.homestore.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    @Override
    public Long saveAddress(Address address) {
        Long id = addressRepository.findAddress(address);

        if(id != null){
            return id;
        }

        return addressRepository.save(address).getId();
    }
}