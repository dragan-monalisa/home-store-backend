package com.homestore.estate.property;

import com.homestore.security.user.User;
import java.util.List;

public interface PropertyService {

    List<PropertyDTO> getProperties(Long userId);

    PropertyDTO getPropertyById(Long id);

    void saveProperty(User user,PropertyRequest request);

    String updateProperty(Long id, PropertyRequest request);

    String deleteProperty(Long id);
}