package com.homestore.security.token.uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UuidTokenRepository extends JpaRepository<UuidToken, Long> {
}