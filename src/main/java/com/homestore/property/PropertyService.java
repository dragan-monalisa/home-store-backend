package com.homestore.property;

import com.homestore.security.user.User;
import java.util.List;
import java.util.Optional;

public interface PropertyService {

    List<PropertyResponse> getProperties(Long userId);

    PropertyResponse getPropertyById(Long id);

    void saveProperty(User user,PropertyRequest request);

    void updateProperty(Long id, PropertyRequest request);

    void deleteProperty(Long id);

    Optional<Property> findById(Long id);
}