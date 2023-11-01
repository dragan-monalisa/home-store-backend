package com.homestore.property;

import com.homestore.ad.category.PropertyCategoryEnum;
import com.homestore.address.Address;
import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnauthorizedAccessException;
import com.homestore.security.user.User;
import com.homestore.util.UpdateHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService{

    private final PropertyRepository propertyRepository;
    private final PropertyDTOMapper propertyMapper;
    private final UpdateHelper updateHelper;

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public Page<PropertyResponse> getProperties(Long userId, Pageable pageable) {
        Page<Property> page = propertyRepository.findByUserId(userId, pageable);

        if(page.isEmpty()){
            throw new ResourceNotFoundException("No property found!");
        }

        return page.map(propertyMapper);
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
    public void updateProperty(Long id, User user, PropertyRequest request) {
        propertyRepository.findById(id)
                .map(property -> {
                    if(!property.getUserId().equals(user.getId())){
                        throw new UnauthorizedAccessException("You are not authorize to update this property!");
                    }

                    String[] nulls = updateHelper.getNullPropertyNames(request);
                    BeanUtils.copyProperties(request, property, nulls);

                    return propertyRepository.save(property);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @Override
    public void deleteProperty(Long id, User user) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found!"));

        if(!property.getUserId().equals(user.getId())){
            throw new UnauthorizedAccessException("You are not authorize to delete this property!");
        }

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