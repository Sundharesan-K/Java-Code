package com.trustrace.security30.repository;

import com.trustrace.security30.pojo.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProviderRepository {
    @Autowired
    private MongoTemplate template;

    public Provider findByName(String name) {
        Provider provider=template.findOne (Query.query (Criteria.where ("name").is (name)),Provider.class);
        return provider;
    }

    public String save(Provider provider) {
        template.save (provider);
        return "Provider Added Successfully";
    }

    public String findAll() {
        template.findAll (Provider.class);
        return "Done";
    }
}
