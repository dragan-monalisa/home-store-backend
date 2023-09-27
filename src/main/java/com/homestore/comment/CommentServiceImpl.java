package com.homestore.comment;

import com.homestore.exception.ResourceNotFoundException;
import com.homestore.exception.UnauthorizedAccessException;
import com.homestore.forum.Forum;
import com.homestore.forum.ForumRepository;
import com.homestore.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ForumRepository forumRepository;
    private final CommentDTOMapper commentMapper;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @Override
    public CommentResponse postComment(User user, CommentRequest request) {
        Forum forum = forumRepository.findById(request.getForumId())
                .orElseThrow(()-> new ResourceNotFoundException("Forum not found!"));

        var comment = Comment.builder()
                .text(request.getText())
                .user(user)
                .forum(forum)
                .build();
        commentRepository.save(comment);

        return commentMapper.apply(comment);
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
    public CommentResponse editComment(User user, Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment not found!"));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedAccessException("You are not authorized to edit this comment!");
        }
        comment.setText(request.getText());

        return commentMapper.apply(commentRepository.save(comment));
    }
}