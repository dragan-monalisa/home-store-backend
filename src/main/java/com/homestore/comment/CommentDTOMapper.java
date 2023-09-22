package com.homestore.comment;

import com.homestore.security.user.UserDTO;
import com.homestore.security.user.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CommentDTOMapper implements Function<Comment, CommentResponse> {

    private final UserDTOMapper userMapper;

    @Override
    public CommentResponse apply(Comment comment) {
        UserDTO user = userMapper.apply(comment.getUser());

        return new CommentResponse(
                comment.getText(),
                comment.getPostedAt(),
                comment.isVisible(),
                user
        );
    }
}