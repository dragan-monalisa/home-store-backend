package com.homestore.user;

import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserResponse> {

    @Override
    public UserResponse apply(User user) {
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().toString()
        );
    }
}