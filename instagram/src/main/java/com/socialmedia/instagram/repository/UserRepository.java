package com.socialmedia.instagram.repository;

import com.socialmedia.instagram.pojo.Post;
import com.socialmedia.instagram.pojo.User;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Repository
public class UserRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    Logger logger;
    private Query query;
    private Update update;
    public User getUserById(String userId) {
        return mongoTemplate.findOne(Query.query(Criteria.where("userId").is(userId)), User.class);
    }
    public User getUserByName(String userName) {
        return mongoTemplate.findOne(Query.query(Criteria.where("userName").is(userName)), User.class);
    }
    public void createUser(User user) { mongoTemplate.save(user); }
    public void deleteUser(String userId) {
        mongoTemplate.remove(Query.query(Criteria.where("userId").is(userId)), User.class);
    }
    public void followUser(String userId, String userIdToFollow) {
        query = new Query().addCriteria(Criteria.where("userId").is(userId));
        System.out.println( userId + " " + getUserById(userId).getFollowingCount());
        update = new Update().set("followingCount", getUserById(userId).getFollowingCount() + 1);
        mongoTemplate.findAndModify(query, update, User.class);

        query = new Query().addCriteria(Criteria.where("userId").is(userIdToFollow));
        System.out.println(userId + " " + getUserById(userIdToFollow).getFollowingCount());
        update = new Update().set("followersCount", getUserById(userIdToFollow).getFollowersCount() + 1);
        mongoTemplate.findAndModify(query, update, User.class);
    }
    public void unfollowUser(String userId, String userIdToUnfollow) { // logic maathanu

        // follow pannama unfollow kulla varatha maathiri logic eludhanu

        query = new Query().addCriteria(Criteria.where("userId").is(userId));
        update = new Update().set("followingCount", getUserById(userId).getFollowingCount() - 1);
        mongoTemplate.findAndModify(query, update, User.class);

        query = new Query().addCriteria(Criteria.where("userId").is(userIdToUnfollow));
        update = new Update().set("followersCount", getUserById(userIdToUnfollow).getFollowersCount() - 1);
        mongoTemplate.findAndModify(query, update, User.class);
    }
    public List<Post> getPostsFromUserId(String userId) {
        Query query = new Query().addCriteria(Criteria.where("userId").is(userId));
        User obj = mongoTemplate.findOne(query, User.class);
        return obj.getPosts();
    }
    public void createPost(String userId, String imageUrl) {
        query = new Query().addCriteria(Criteria.where("userId").is(userId));

        List<Post> posts = getUserById(userId).getPosts();
        posts.add(new Post( 100,imageUrl));

//        update = new Update().set("posts", getPostsFromUserId(userId).add(new Post(imageUrl)));
        update = new Update().set("posts", posts).inc("totalNumberOfPost", 1);
        mongoTemplate.findAndModify(query, update, User.class);
    }
    public void deletePost(String userId, String postId) {
//        mongoTemplate.remove()
    }
}