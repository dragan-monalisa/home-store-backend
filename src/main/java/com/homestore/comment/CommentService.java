package com.homestore.comment;

import com.homestore.security.user.User;
import java.util.List;

public interface CommentService {
    List<CommentDTO> getAllComments();
    String addComment(User user, CommentRequest request);
}
