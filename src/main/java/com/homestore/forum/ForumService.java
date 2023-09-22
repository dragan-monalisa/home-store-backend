package com.homestore.forum;

import java.util.List;

public interface ForumService {

    List<ForumResponse> getForums();

    ForumResponse getForum(Integer id);

    ForumResponse saveForum(ForumRequest request);

    ForumResponse editForum(Integer id, ForumRequest request);
}