package com.homestore.estate.property;

import com.homestore.security.user.User;
import java.util.List;

public interface PropertyService {
    List<PropertyDTO> getAllProperties();
    String saveProperty(User user,PropertyRequest request);
}
