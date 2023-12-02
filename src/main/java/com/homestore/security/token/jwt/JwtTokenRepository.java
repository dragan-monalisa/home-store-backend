package com.homestore.security.token.jwt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    @Query("SELECT t " +
            "FROM JwtToken t " +
            "WHERE t.user.id = :id")
    List<JwtToken> findAllValidTokenByUser(Long id);
}