package com.homestore.comment;

import com.homestore.security.user.User;

public interface CommentService {

    String postComment(User user, CommentRequest request);

    String deleteComment(User user, Long commentId);

    String editComment(User user, Long commentId, CommentRequest request);
}