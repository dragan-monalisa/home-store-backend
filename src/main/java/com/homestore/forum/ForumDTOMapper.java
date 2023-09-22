package com.homestore.forum;

import com.homestore.comment.CommentDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumDTOMapper implements Function<Forum, ForumResponse> {

    private final CommentDTOMapper commentMapper;

    @Override
    public ForumResponse apply(Forum forum) {

        return new ForumResponse(
                forum.getName(),
                forum.getComments()
                        .stream()
                        .map(commentMapper)
                        .collect(Collectors.toList())
        );
    }
}