package com.app.Instagram2K24.dao.impl;

import com.app.Instagram2K24.dao.AdminDao;
import com.app.Instagram2K24.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminDaoImpl implements AdminDao {
    private final MongoTemplate mongoTemplate;
    public Admin findAdmin(String emailId){
        Query query = new Query();
        query.addCriteria(Criteria.where("email_id").is(emailId));
        return mongoTemplate.findOne(query, Admin.class);
    }

    @Override
    public void createAdmin(Admin admin) {
        mongoTemplate.save(admin);
    }
}
