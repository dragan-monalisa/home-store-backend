package com.homestore.forum;

import com.homestore.exception.ResourceConflictException;
import com.homestore.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumServiceImpl implements ForumService{

    private final ForumRepository forumRepository;
    private final ForumDTOMapper forumMapper;

    @Override
    public List<ForumResponse> getForums() {
        List<Forum> forums = forumRepository.findAll();

        forums.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No forum found!"));

        return forums
                .stream()
                .map(forumMapper)
                .collect(Collectors.toList());
    }

    @Override
    public ForumResponse getForum(Long id) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forum not found!"));

        return forumMapper.apply(forum);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void saveForum(ForumRequest request) {
        forumRepository.findByName(ForumNameEnum.valueOf(request.getName())).ifPresent(forum -> {
            throw new ResourceConflictException("Forum name already exist!");
        });

        var forum = Forum.builder()
                .name(ForumNameEnum.valueOf(request.getName()))
                .build();

        forumRepository.save(forum);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void editForum(Long id, ForumRequest request) {
        Forum forum = forumRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Forum not found!"));

        forum.setName(ForumNameEnum.valueOf(request.getName()));
        forumRepository.save(forum);
    }

    @Override
    public Optional<Forum> findForumById(Long id) {
        return forumRepository.findById(id);
    }
}