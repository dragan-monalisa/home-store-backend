package com.homestore.estate.property;

import com.homestore.estate.ad.category.EstateCategory;
import com.homestore.estate.address.Address;
import com.homestore.estate.address.AddressService;
import com.homestore.estate.property.apartment.Apartment;
import com.homestore.estate.property.apartment.ApartmentRepository;
import com.homestore.estate.property.house.House;
import com.homestore.estate.property.house.HouseRepository;
import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final ApartmentRepository apartmentRepository;
    private final HouseRepository houseRepository;
    private final PropertyDTOMapper propertyMapper;
    private final AddressService addressService;

    @Override
    public List<PropertyDTO> getProperties(Long userId) {
        return propertyRepository.findByUserId(userId)
                .stream()
                .map(propertyMapper)
                .collect(Collectors.toList());
    }

    @Override
    public PropertyDTO getPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);

        return property.map(propertyMapper).orElse(null);
    }

    @Transactional
    @Override
    public void saveProperty(User user, PropertyRequest request) {
        Address address = Address.builder()
                .county(request.getCounty())
                .city(request.getCity())
                .street(request.getCity())
                .number(request.getNumber())
                .build();
        Long addressId = addressService.saveAddress(address);
        address.setId(addressId);

        var property = Property.builder()
                .usableArea(request.getUsableArea())
                .description(request.getDescription())
                .userId(user.getId())
                .address(address)
                .build();
        propertyRepository.save(property);

        if(EstateCategory.HOUSE.name().equals(request.getCategory().name())){
            saveHouse(property.getId(), request);
        }
        else if(EstateCategory.APARTMENT.name().equals(request.getCategory().name())){
            saveApartment(property.getId(), request);
        }
    }

    @Override
    public String updateProperty(Long id, PropertyRequest request) {
        Property property = propertyRepository.findById(id).orElse(null);

        if(property != null && request.getDescription() != null){
            property.setDescription(request.getDescription());
            propertyRepository.save(property);

            return ResponseEnum.UPDATED.name();
        }

        return ResponseEnum.BAD_REQUEST.name();
    }

    @Override
    public String deleteProperty(Long id) {
        Property property = propertyRepository.findById(id).orElse(null);

        if(property != null){
            propertyRepository.delete(property);

            return ResponseEnum.DELETED.name();
        }

            return ResponseEnum.NOT_FOUND.name();
    }


    private void saveHouse(Long propertyId, PropertyRequest request) {
        var house = House.builder()
                .propertyId(propertyId)
                .storeys(request.getStoreys())
                .buildYear(request.getBuildYear())
                .roomsNumber(request.getRoomsNumber())
                .build();
        houseRepository.save(house);
    }

    private void saveApartment(Long propertyId, PropertyRequest request) {
        var apartment = Apartment.builder()
                .propertyId(propertyId)
                .partitioning(request.getPartitioning())
                .floor(request.getFloor())
                .buildYear(request.getBuildYear())
                .roomsNumber(request.getRoomsNumber())
                .build();
        apartmentRepository.save(apartment);
    }
}