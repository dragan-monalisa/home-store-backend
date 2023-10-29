package com.homestore.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT r " +
            "FROM Rental r " +
            "WHERE r.ad.realtorId = :realtorId ")
    List<Rental> findAllByRealtor(Long realtorId);
}