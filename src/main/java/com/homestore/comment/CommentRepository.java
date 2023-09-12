package com.homestore.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT " +
                "CASE WHEN COUNT(ad.user.id) > 0 " +
                "THEN true " +
                "ELSE false " +
            "END " +
            "FROM estate_ad ad " +
            "WHERE ad.id IN " +
            "(SELECT a.id FROM estate_ad a INNER JOIN Sale s ON a.id = s.id)" +
            "AND ad.user.id = ?1")
    boolean didUserGaveForSale(Long userId);

    @Query("SELECT " +
                "CASE WHEN COUNT(ad.user.id) > 0 " +
                "THEN true " +
                "ELSE false " +
            "END " +
            "FROM estate_ad ad " +
            "WHERE ad.id IN " +
            "(SELECT a.id FROM estate_ad a INNER JOIN Rental r ON a.id = r.id) " +
            "AND ad.user.id = ?1")
    boolean didUserGaveForRent(Long userId);

    @Query("SELECT " +
                "CASE WHEN COUNT(r.tenantId) > 0 " +
                "THEN true " +
                "ELSE false " +
            "END " +
            "FROM Rental r " +
            "WHERE r.tenantId = ?1")
    boolean didUserRented(Long id);

    @Query("SELECT " +
                "CASE WHEN COUNT(s.buyerId) > 0 " +
                "THEN true " +
                "ELSE false " +
            "END " +
            "FROM Sale s " +
            "WHERE s.buyerId = ?1")
    boolean didUserBought(Long id);
}