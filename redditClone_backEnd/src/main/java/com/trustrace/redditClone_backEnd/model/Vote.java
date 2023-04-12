package com.trustrace.redditClone_backEnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "VoteList")
public class Vote {
    @Id
    private Long voteId;
    private VoteType voteType;
    private Post post;
    private User user;
}
