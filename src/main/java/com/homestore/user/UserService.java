package com.homestore.user;

import com.homestore.security.auth.request.PasswordRequest;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);

    void changePassword(User user, PasswordRequest request);

    boolean userExists(String email);

    boolean isEmailValid(String email);

    User save(User user);

    Optional<User> findByEmail(String email);
}