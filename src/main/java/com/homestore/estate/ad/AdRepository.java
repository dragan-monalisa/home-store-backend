package com.homestore.estate.ad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT a " +
            "FROM estate_ads a " +
            "WHERE a.status = 'active' ")
    List<Ad> findAllAds();

    @Query("SELECT a " +
            "FROM estate_ads a " +
            "INNER JOIN ads_category c ON a.category.id = c.id " +
            "INNER JOIN properties p ON a.property.id = p.id " +
            "INNER JOIN addresses ad ON p.addressId = ad.id " +
            "WHERE (:#{#criteria.category} is NULL OR c.category = :#{#criteria.category}) " +
            "AND (:#{#criteria.type} is NULL OR c.type = :#{#criteria.type}) " +
            "AND (:#{#criteria.county} is NULL OR ad.county = :#{#criteria.county}) " +
            "AND (:#{#criteria.city} is NULL OR ad.city = :#{#criteria.city}) " +
            "AND (:#{#criteria.minPrice} is NULL OR a.price >= :#{#criteria.minPrice}) " +
            "AND (:#{#criteria.maxPrice} is NULL OR a.price <= :#{#criteria.maxPrice}) " +
            "AND (:#{#criteria.minUsableArea} is NULL OR p.usableArea >= :#{#criteria.minUsableArea}) " +
            "AND (:#{#criteria.maxUsableArea} is NULL OR p.usableArea <= :#{#criteria.maxUsableArea}) " +
            "AND a.status = 'active'")
    List<Ad> getAdsByCriteria(@Param("criteria") AdSearchCriteria adCriteria);
}