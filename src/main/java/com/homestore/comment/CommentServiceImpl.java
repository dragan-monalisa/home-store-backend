package com.homestore.comment;

import com.homestore.forum.Forum;
import com.homestore.forum.ForumRepository;
import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ForumRepository forumRepository;

    @Override
    public String postComment(User user, CommentRequest request) {
        Forum forum = forumRepository.findById(request.getForumId()).orElse(null);

        if (request.getText() != null && forum != null) {
            var comment = Comment.builder()
                    .text(request.getText())
                    .user(user)
                    .postedAt(LocalDateTime.now())
                    .forum(forum)
                    .build();
            commentRepository.save(comment);

            return ResponseEnum.ADDED.name();
        }
        return ResponseEnum.BAD_REQUEST.name();
    }

    @Override
    public String deleteComment(User user, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment != null) {
            if (comment.getUser().getId().equals(user.getId())) {
                commentRepository.deleteById(commentId);

                return ResponseEnum.DELETED.name();
            }

            return ResponseEnum.UNAUTHORIZED.name();
        }

        return ResponseEnum.NOT_FOUND.name();
    }

    @Override
    public String editComment(User user, Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment != null && request.getText() != null) {
            if (comment.getUser().getId().equals(user.getId())) {
                comment.setText(request.getText());
                commentRepository.save(comment);

                return ResponseEnum.UPDATED.name();
            }

            return ResponseEnum.UNAUTHORIZED.name();
        }

        return ResponseEnum.BAD_REQUEST.name();
    }
}