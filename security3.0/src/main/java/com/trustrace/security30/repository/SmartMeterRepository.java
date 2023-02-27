package com.trustrace.security30.repository;

import com.trustrace.security30.pojo.SmartMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SmartMeterRepository {
     @Autowired
     private MongoTemplate template;

    public SmartMeter findByMeterId(String meterId){
       return template.findOne (Query.query (Criteria.where ("meterId").is (meterId)),SmartMeter.class);
    }

     public String  save(SmartMeter smartMeter) {
         template.save (smartMeter);
         return "Successfully";
     }

     public void getAllSmartMeters() {
        template.findAll (SmartMeter.class);
     }
}
