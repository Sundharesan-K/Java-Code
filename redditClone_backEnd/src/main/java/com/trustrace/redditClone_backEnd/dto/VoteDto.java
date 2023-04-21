package com.trustrace.redditClone_backEnd.dto;

import com.trustrace.redditClone_backEnd.model.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {
    private VoteType voteType;
    private String postId;
}
