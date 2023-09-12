package com.homestore.estate.property;

import com.homestore.estate.address.Address;
import com.homestore.estate.address.AddressService;
import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final PropertyDTOMapper propertyMapper;
    private final AddressService addressService;

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll()
                .stream()
                .map(propertyMapper)
                .collect(Collectors.toList());
    }

    @Override
    public String saveProperty(User user, PropertyRequest request) {
        var address = Address.builder()
                .county(request.getCounty())
                .city(request.getCity())
                .street(request.getCity())
                .number(request.getNumber())
                .build();
        Long addressId = addressService.saveAddress(address);

        var property = Property.builder()
                .usableArea(request.getUsableArea())
                .description(request.getDescription())
                .userId(user.getId())
                .addressId(addressId)
                .build();

        return ResponseEnum.SAVED.name();
    }
}
