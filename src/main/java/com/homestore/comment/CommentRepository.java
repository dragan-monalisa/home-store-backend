package com.homestore.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
//    boolean didUserGaveForSale(Long userId);
//
//    boolean didUserGaveForRent(Long userId);
//
//    boolean didUserRented(Long id);
//
//    boolean didUserBought(Long id);
}