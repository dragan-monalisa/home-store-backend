package com.homestore.property;

import com.homestore.security.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface PropertyService {

    Page<PropertyResponse> getProperties(Long userId, Pageable pageable);

    PropertyResponse getPropertyById(Long id);

    void saveProperty(User user,PropertyRequest request);

    void updateProperty(Long id, PropertyRequest request);

    void deleteProperty(Long id);

    Optional<Property> findById(Long id);
}