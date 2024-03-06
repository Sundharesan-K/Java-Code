package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.ConsumerDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsumerDaoImpl implements ConsumerDao {
    @Autowired
    private MongoTemplate template;

    @Override
    public Consumer findConsumer(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return template.findOne(query, Consumer.class);
    }

    @Override
    public void addConsumer(Consumer consumer) {
        template.insert(consumer);
    }

    @Override
    public List<Consumer> findAllConsumer() {
        return template.findAll(Consumer.class);
    }

    @Override
    public Consumer findById(String id) {
        return template.findById(id, Consumer.class);
    }

    @Override
    public void updateSmartMeterForConsumer(String consumerId, List<String> meterId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(consumerId));
        Update update = new Update();
        update.set("meterId", meterId);
        template.updateFirst(query, update, Consumer.class);
    }
}
