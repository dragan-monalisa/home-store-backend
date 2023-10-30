package com.homestore.rental;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT r " +
            "FROM Rental r " +
            "WHERE r.ad.realtorId = :realtorId ")
    Page<Rental> findAllByRealtor(Long realtorId, Pageable pageable);
}