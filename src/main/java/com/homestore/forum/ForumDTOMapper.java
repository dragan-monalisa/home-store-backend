package com.homestore.forum;

import com.homestore.comment.CommentDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumDTOMapper implements Function<Forum, ForumDTO> {

    private final CommentDTOMapper commentMapper;

    @Override
    public ForumDTO apply(Forum forum) {

        return new ForumDTO(
                forum.getId(),
                forum.getName(),
                forum.getComments()
                        .stream()
                        .map(commentMapper)
                        .collect(Collectors.toList())
        );
    }
}