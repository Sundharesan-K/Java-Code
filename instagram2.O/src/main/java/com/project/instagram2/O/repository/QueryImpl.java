package com.project.instagram2.O.repository;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public interface QueryImpl {
    default Query getQueryForUserId(String userId){
        return Query.query(Criteria.where("userId").is(userId));
    }

    default Query getQueryForUserName(String userName){
        return Query.query(Criteria.where("userName").is(userName));
    }

    default Query getQueryForPostId(String postId){
        return Query.query(Criteria.where("postId").is(postId));
    }

    default Query getQueryForEmail(String email){
        return Query.query(Criteria.where("email").is(email));
    }

}
