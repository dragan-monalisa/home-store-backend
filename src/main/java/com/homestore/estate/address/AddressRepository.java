package com.homestore.estate.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a.id " +
            "FROM Address a " +
            "WHERE a.county = :#{#criteria.county} " +
            "AND a.city = :#{#criteria.city} " +
            "AND a.street = :#{#criteria.street} " +
            "AND a.number = :#{#criteria.number} ")
    Long findAddress(@Param("criteria") Address address);
}
