package com.homestore.forum;

import java.util.List;

public interface ForumService {

    List<ForumDTO> getForums();

    ForumDTO getForum(Integer id);

    ForumDTO saveForum(ForumRequest request);

    ForumDTO editForum(Integer id, ForumRequest request);
}