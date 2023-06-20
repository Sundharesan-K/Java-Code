package com.trustrace.redditClone_backEnd.controller;

import com.trustrace.redditClone_backEnd.Service.SubredditService;
import com.trustrace.redditClone_backEnd.dto.SubredditDto;
import com.trustrace.redditClone_backEnd.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
@Slf4j
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping("/create")
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){
      return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subredditDto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SubredditDto>> getAllSubreddits(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }

}
