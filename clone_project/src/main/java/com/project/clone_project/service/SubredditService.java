package com.project.clone_project.service;

import com.project.clone_project.dto.SubredditDto;
import com.project.clone_project.exception.SpringRedditException;
import com.project.clone_project.mapper.SubredditMapper;
import com.project.clone_project.model.Subreddit;
import com.project.clone_project.repository.SubredditRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubredditService {
    private final SubredditRepo subredditRepo;
    private final SubredditMapper subredditMapper;
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepo.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    public List<SubredditDto> getAll() {
        return subredditRepo.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .toList();
    }

    public SubredditDto getById(String id) {
        Subreddit subreddit = subredditRepo.findById(id)
                .orElseThrow(()-> new SpringRedditException("No subreddit found this "+id+"id"));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
