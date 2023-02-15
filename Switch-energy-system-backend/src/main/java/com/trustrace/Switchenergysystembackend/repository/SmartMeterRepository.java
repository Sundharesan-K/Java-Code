package com.trustrace.Switchenergysystembackend.repository;

import com.trustrace.Switchenergysystembackend.entity.SmartMeter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmartMeterRepository extends MongoRepository<SmartMeter,String > {

    SmartMeter findMeterIdAndStatus(String meterId, String status);

    List<SmartMeter> findByStatus(String status);

    List<SmartMeter> findEmailIdAndStatus(String emailId, String status);

}
