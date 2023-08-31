package com.homestore.comment;

import com.homestore.security.user.UserDTO;
import com.homestore.security.user.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CommentDTOMapper implements Function<Comment, CommentDTO> {

    private final UserDTOMapper userMapper;

    @Override
    public CommentDTO apply(Comment comment) {
        UserDTO user = userMapper.apply(comment.getUser());

        return new CommentDTO(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                user
        );
    }
}