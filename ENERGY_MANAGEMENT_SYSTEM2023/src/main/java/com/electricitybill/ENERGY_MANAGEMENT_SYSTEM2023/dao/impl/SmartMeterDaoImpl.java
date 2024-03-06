package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.SmartMeterDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.SmartMeter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SmartMeterDaoImpl implements SmartMeterDao {
    private final MongoTemplate mongoTemplate;

    @Override
    public SmartMeter findSmartMeter(String meterId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meter_id").is(meterId));
        return mongoTemplate.findOne(query, SmartMeter.class);
    }

    @Override
    public void addSmartMeter(SmartMeter smartMeter) {
        mongoTemplate.insert(smartMeter);
    }

    @Override
    public void updateSmartMeterReadings(String meterId, ReadingDto readingDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meter_id").is(meterId));
        SmartMeter smartMeter = mongoTemplate.findOne(query, SmartMeter.class);
        String provider = smartMeter.getProvider();
        Update update = new Update();
        readingDto.setProvider(provider);
        update.push("readings", readingDto);
        mongoTemplate.updateFirst(query, update, SmartMeter.class);
    }

    @Override
    public void updateStatus(String meterId, String status) {
        Query query = new Query();
        query.addCriteria(Criteria.where("meter_id").is(meterId));
        Update update = new Update();
        update.set("status", status);
        mongoTemplate.updateFirst(query, update, SmartMeter.class);
    }

    @Override
    public List<SmartMeter> getAllSmartmeter() {
        return mongoTemplate.findAll(SmartMeter.class);
    }
}
