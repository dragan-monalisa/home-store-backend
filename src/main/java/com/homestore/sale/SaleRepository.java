package com.homestore.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s " +
            "FROM Sale s " +
            "INNER JOIN property_ad a " +
            "ON s.ad.id = a.id " +
            "WHERE a.realtorId = :realtorId ")
    List<Sale> findAllByRealtor(Long realtorId);
}