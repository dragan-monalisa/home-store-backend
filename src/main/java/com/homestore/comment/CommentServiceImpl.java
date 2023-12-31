package com.homestore.comment;

import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnauthorizedAccessException;
import com.homestore.forum.Forum;
import com.homestore.forum.ForumService;
import com.homestore.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ForumService forumService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public void postComment(User user, CommentRequest request) {
        Forum forum = forumService.findForumById(request.getForumId())
                .orElseThrow(()-> new ResourceNotFoundException("Forum not found!"));

        var comment = Comment.builder()
                .text(request.getText())
                .user(user)
                .forum(forum)
                .build();

        commentRepository.save(comment);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public void deleteComment(User user, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found!"));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You are not authorized to delete this comment!");
        }

        commentRepository.delete(comment);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public void editComment(User user, Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found!"));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You are not authorized to edit this comment!");
        }
        comment.setText(request.getText());

        commentRepository.save(comment);
    }
}