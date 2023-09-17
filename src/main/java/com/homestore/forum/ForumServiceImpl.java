package com.homestore.forum;

import com.homestore.utils.ResponseEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService{

    private final ForumRepository forumRepository;
    private final ForumDTOMapper forumMapper;

    @Override
    public ForumDTO getForumByName(String name) {
        Optional<Forum> forum = forumRepository.findForumByName(ForumNameEnum.valueOf(name));

        return forum.map(forumMapper).orElse(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String saveForum(ForumRequest request) {
        boolean isPresent = forumRepository.findForumByName(ForumNameEnum.valueOf(request.getName())).isPresent();

        if(!isPresent){
            var forum = Forum.builder()
                    .name(ForumNameEnum.valueOf(request.getName()))
                    .build();
            forumRepository.save(forum);

            return ResponseEnum.SAVED.name();
        }

        return ResponseEnum.ALREADY_EXISTS.name();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public String editForumName(Integer id, ForumRequest request) {
        Forum forum = forumRepository.findById(id).orElse(null);

        if(forum != null && request.getName() != null){
            forum.setName(ForumNameEnum.valueOf(request.getName()));
            forumRepository.save(forum);

            return ResponseEnum.UPDATED.name();
        }

        return ResponseEnum.BAD_REQUEST.name();
    }
}