package com.homestore.forum;

import java.util.List;

public interface ForumService {

    List<ForumResponse> getForums();

    ForumResponse getForum(Integer id);

    void saveForum(ForumRequest request);

    void editForum(Integer id, ForumRequest request);
}