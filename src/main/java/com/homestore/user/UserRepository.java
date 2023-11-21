package com.homestore.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User " +
            "SET password = :password, updatedAt = CURRENT_TIMESTAMP " +
            "WHERE email = :email")
    void changePassword(String email, String newPassword);
}