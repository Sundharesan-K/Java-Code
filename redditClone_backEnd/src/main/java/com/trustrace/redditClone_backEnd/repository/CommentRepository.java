package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment,Long> {
}
