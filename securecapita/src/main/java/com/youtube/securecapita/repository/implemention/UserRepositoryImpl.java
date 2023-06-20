package com.youtube.securecapita.repository.implemention;

import com.youtube.securecapita.domain.User;
import com.youtube.securecapita.exception.ApiException;
import com.youtube.securecapita.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl implements UserRepository<User> {

    private static final String COUNT_USER_EMAIL_QUERY = "";
    private final MongoTemplate mongoTemplate;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public User create(User user) {
        // Check the email Unique
        if (getEmailCount(user.getEmail().trim().toLowerCase())>0) throw new ApiException("Email already in use. Please use a different email and try again");
        // Save new User
        // Add role to the user
        // Send verification URL
        // Save URL in verification table
        // Send email to user with verification URL
        // Return the newly create user
        // If any errors, throw exception with proper message
        return null;
    }

    @Override
    public Collection<User> list(int page, int pageSize) {
        return null;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    private Long getEmailCount(String email) {
        Criteria criteria = Criteria.where("email").is(email);
        Query query = new Query(criteria);
        Long count = mongoTemplate.count(query,Integer.class,COUNT_USER_EMAIL_QUERY);
        return count;
    }
}
