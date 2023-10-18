package com.homestore.property;

import com.homestore.ad.category.PropertyCategoryEnum;
import com.homestore.address.Address;
import com.homestore.address.AddressService;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.security.user.User;
import com.homestore.util.UpdateHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final PropertyDTOMapper propertyMapper;
    private final AddressService addressService;
    private final UpdateHelper updateHelper;

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public List<PropertyResponse> getProperties(Long userId) {
        List<Property> properties = propertyRepository.findByUserId(userId);

        if(properties.isEmpty()){
            throw new ResourceNotFoundException("No property found!");
        }

        return properties
                .stream()
                .map(propertyMapper)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public PropertyResponse getPropertyById(Long id) {
        Optional<Property> property = propertyRepository.findById(id);

        return property.map(propertyMapper)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Transactional
    @Override
    public void saveProperty(User user, PropertyRequest request) {
        Address address = createAddress(request);
        address.setId(addressService.saveAddress(address));

        Property property = createProperty(user, request, address);

        if(PropertyCategoryEnum.HOUSE.name().equals(request.getCategory().name()) || PropertyCategoryEnum.APARTMENT.name().equals(request.getCategory().name())){
            property.setBuildYear(request.getBuildYear());
            property.setRoomsNumber(request.getRoomsNumber());
            property.setBathroomsNumber(request.getBathroomsNumber());
            property.setPartitioning(request.getPartitioning());

            if(PropertyCategoryEnum.HOUSE.name().equals(request.getCategory().name())){
                property.setFloorsNumber(request.getFloorsNumber());
            }
            else{
                property.setFloor(request.getFloor());
            }
        }

        propertyRepository.save(property);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void updateProperty(Long id, PropertyRequest request) {
        propertyRepository.findById(id)
                .map(property -> {
                    String[] nulls = updateHelper.getNullPropertyNames(request);
                    BeanUtils.copyProperties(request, property, nulls);

                    return propertyRepository.save(property);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void deleteProperty(Long id) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));

       propertyRepository.delete(property);
    }

    @Override
    public Optional<Property> findById(Long id) {
        return propertyRepository.findById(id);
    }

    private Property createProperty(User user, PropertyRequest request, Address address){
        return Property.builder()
                .area(request.getArea())
                .description(request.getDescription())
                .category(request.getCategory())
                .userId(user.getId())
                .address(address)
                .build();
    }

    private Address createAddress(PropertyRequest request){
        return Address.builder()
                .county(request.getCounty())
                .city(request.getCity())
                .street(request.getStreet())
                .number(request.getNumber())
                .build();
    }
}