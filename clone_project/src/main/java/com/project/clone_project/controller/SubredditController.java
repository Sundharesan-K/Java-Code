package com.project.clone_project.controller;

import com.project.clone_project.dto.SubredditDto;
import com.project.clone_project.service.SubredditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subreddit")
public class SubredditController {
    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.save(subredditDto));
    }

    @GetMapping("/getAllSubreddit")
    public ResponseEntity<List<SubredditDto>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(subredditService.getById(id));
    }
}
