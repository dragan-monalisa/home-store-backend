package com.homestore.ad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("SELECT a " +
            "FROM property_ad a " +
            "INNER JOIN Property p ON a.property.id = p.id " +
            "INNER JOIN Address ad ON p.address.id = ad.id " +
            "WHERE (:#{#criteria.adCategory} is NULL OR a.category = :#{#criteria.adCategory}) " +
            "AND (:#{#criteria.propertyCategory} is NULL OR p.category = :#{#criteria.propertyCategory}) " +
            "AND (:#{#criteria.county} is NULL OR ad.county = :#{#criteria.county}) " +
            "AND (:#{#criteria.city} is NULL OR ad.city = :#{#criteria.city}) " +
            "AND (:#{#criteria.minPrice} is NULL OR a.price >= :#{#criteria.minPrice}) " +
            "AND (:#{#criteria.maxPrice} is NULL OR a.price <= :#{#criteria.maxPrice}) " +
            "AND (:#{#criteria.minUsableArea} is NULL OR p.area >= :#{#criteria.minUsableArea}) " +
            "AND (:#{#criteria.maxUsableArea} is NULL OR p.area <= :#{#criteria.maxUsableArea}) " +
            "AND a.status = 'ACTIVE' ")
    Page<Ad> getAdsByFilters(@Param("criteria") SearchCriteria adCriteria, Pageable pageable);

    @Query("SELECT a " +
            "FROM property_ad a " +
            "WHERE a.status = 'ACTIVE' ")
    Page<Ad> findAllAds(Pageable pageable);

    Page<Ad> findAllByUserIdAndStatus(Long userId, StatusEnum status, Pageable pageable);

    Page<Ad> findAllByRealtorIdAndStatus(Long id, StatusEnum status, Pageable pageable);
}