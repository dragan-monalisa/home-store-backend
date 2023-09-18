package com.homestore.estate.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("SELECT p " +
            "FROM Property p " +
            "WHERE p.userId = :id ")
    List<Property> findByUserId(Long id);
}