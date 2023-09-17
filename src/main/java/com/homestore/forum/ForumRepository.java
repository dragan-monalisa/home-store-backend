package com.homestore.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer> {

    @Query("SELECT f " +
            "FROM Forum f " +
            "WHERE f.name = :name")
    Optional<Forum> findForumByName(ForumNameEnum name);
}