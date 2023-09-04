package com.homestore.comment;

import com.homestore.security.user.User;
import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentDTOMapper commentMapper;

    @Override
    public List<CommentDTO> getAllComments(){
        return commentRepository.findAll()
                .stream()
                .map(commentMapper)
                .collect(Collectors.toList());
    }

    @Override
    public String addComment(User user, CommentRequest request){
        if(isUserAbleToAddComment(user)) {
            var comment = Comment.builder()
                    .text(request.getText())
                    .user(user)
                    .createdAt(LocalDateTime.now())
                    .build();
            commentRepository.save(comment);

            return ResponseEnum.ADDED.name();
        }
        return ResponseEnum.BAD_REQUEST.name();
    }

    private boolean isUserAbleToAddComment(User user){
        boolean userGaveForSale = commentRepository.didUserGaveForSale(user.getId());
        boolean userGaveForRent = commentRepository.didUserGaveForRent(user.getId());
        boolean userRented = commentRepository.didUserRented(user.getId());
        boolean userBought = commentRepository.didUserBought(user.getId());

        return userGaveForSale ||
                userGaveForRent ||
                userRented ||
                userBought;
    }
}