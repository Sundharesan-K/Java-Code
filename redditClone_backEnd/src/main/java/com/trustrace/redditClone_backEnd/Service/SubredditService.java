package com.trustrace.redditClone_backEnd.Service;

import com.trustrace.redditClone_backEnd.dto.SubredditDto;
import com.trustrace.redditClone_backEnd.exceptions.SpringRedditException;
import com.trustrace.redditClone_backEnd.mapper.SubredditMapper;
import com.trustrace.redditClone_backEnd.model.Subreddit;
import com.trustrace.redditClone_backEnd.repository.SubredditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    private SubredditMapper subredditMapper;

    public SubredditDto save(SubredditDto subredditDto){
      Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
      subredditDto.setId(save.getId());
      return subredditDto;
    }

    public List<SubredditDto> getAll() {
        List<Subreddit> findAll = subredditRepository.findAll();
         return findAll
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .toList();
    }

    public SubredditDto getSubreddit(String id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
