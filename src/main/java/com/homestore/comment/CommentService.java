package com.homestore.comment;

import com.homestore.security.user.User;

public interface CommentService {

    void postComment(User user, CommentRequest request);

    void deleteComment(User user, Long commentId);

    void editComment(User user, Long commentId, CommentRequest request);
}