package com.homestore.security.user;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
}
