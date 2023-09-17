package com.homestore.forum;

public interface ForumService {

    ForumDTO getForumByName(String name);

    String saveForum(ForumRequest request);

    String editForumName(Integer id, ForumRequest request);
}