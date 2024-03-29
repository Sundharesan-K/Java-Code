package com.trustrace.Switchenergysystembackend.repository;

import com.trustrace.Switchenergysystembackend.entity.SmartMeter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartMeterRepository extends MongoRepository<SmartMeter,String > {

    SmartMeter findByMeterIdAndStatus(String meterId, String status);

    List<SmartMeter> findByStatus(String status);

    List<SmartMeter> findByEmailIdAndStatus(String emailId, String status);

}
