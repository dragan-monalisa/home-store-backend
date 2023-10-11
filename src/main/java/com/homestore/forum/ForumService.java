package com.homestore.forum;

import java.util.List;
import java.util.Optional;

public interface ForumService {

    List<ForumResponse> getForums();

    ForumResponse getForum(Long id);

    void saveForum(ForumRequest request);

    void editForum(Long id, ForumRequest request);

    Optional<Forum> findForumById(Long id);
}