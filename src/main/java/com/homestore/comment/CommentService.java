package com.homestore.comment;

import com.homestore.security.user.User;

public interface CommentService {

    CommentResponse postComment(User user, CommentRequest request);

    void deleteComment(User user, Long commentId);

    CommentResponse editComment(User user, Long commentId, CommentRequest request);
}