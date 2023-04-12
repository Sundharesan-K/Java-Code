package com.trustrace.redditClone_backEnd.repository;

import com.trustrace.redditClone_backEnd.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post,Long> {
}
