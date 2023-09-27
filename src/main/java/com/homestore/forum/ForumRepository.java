package com.homestore.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer> {
    
    Optional<Forum> findByName(ForumNameEnum name);
}