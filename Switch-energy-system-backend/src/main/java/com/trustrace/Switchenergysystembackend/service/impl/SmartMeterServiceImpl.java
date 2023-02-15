package com.trustrace.Switchenergysystembackend.service.impl;

import com.trustrace.Switchenergysystembackend.entity.Provider;
import com.trustrace.Switchenergysystembackend.entity.Reading;
import com.trustrace.Switchenergysystembackend.entity.SmartMeter;
import com.trustrace.Switchenergysystembackend.repository.ProviderRepository;
import com.trustrace.Switchenergysystembackend.repository.SmartMeterRepository;
import com.trustrace.Switchenergysystembackend.service.SmartMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.*;
@Service
public class SmartMeterServiceImpl implements SmartMeterService {
    @Autowired
    private SmartMeterRepository smartMeterRepository;
    @Autowired
    private ProviderRepository providerRepository;
    @Override
    public SmartMeter createSmartMeter(SmartMeter smartMeter) {
        SmartMeter smartMeter1=smartMeterRepository.findMeterIdAndStatus(smartMeter.getMeterId (),"enabled");
        if (smartMeter1!=null){
            throw new RuntimeException ("Smart meter Already exists");
        }
        smartMeter.setStatus ("Waiting Access");
        return smartMeterRepository.save (smartMeter);

    }

    @Override
    public List<SmartMeter> getAllSmartMeters() {
        return smartMeterRepository.findAll ();
    }

    @Override
    public List<SmartMeter> getUserSmartMeters(String userId, String status) {
        return smartMeterRepository.findEmailIdAndStatus(userId,status);
    }

    @Override
    public List<SmartMeter> getSmartMetersByStatus(String status) {
        return smartMeterRepository.findByStatus(status);
    }

    @Override
    public SmartMeter changeProvider(String meterId, String providerName) {
        return null;
    }

    @Override
    public SmartMeter insertReading(String meterId, double reading) {
        SmartMeter smartMeter=smartMeterRepository.findMeterIdAndStatus (meterId,"enabled");
        if (smartMeter==null){
            throw new RuntimeException ("Not Found");
        }
        if (Objects.isNull (smartMeter.getMeterId ())){
            smartMeter.setReadings (new ArrayList<> ());
        }
        smartMeter.getReadings ().add (new Reading (new Date (),reading));
        return smartMeterRepository.save (smartMeter);
    }

    @Override
    public SmartMeter changeStatus(String id, String status) {
        SmartMeter smartMeter = null;
        smartMeter = smartMeterRepository.findMeterIdAndStatus(id, "enabled");

        if (smartMeter == null) {
            smartMeter = smartMeterRepository.findMeterIdAndStatus(id, "pending_approval");
            if (smartMeter == null) {
                throw new RuntimeException("Smart meter not found");
            }
        }

        smartMeter.setStatus(status);
        return smartMeterRepository.save(smartMeter);

    }

    @Override
    public Double calculate(String meterId) throws Exception {
        try{
            SmartMeter smartMeter=smartMeterRepository.findMeterIdAndStatus (meterId,"enabled");
            List<Reading> readings=smartMeter.getReadings ();
            int previousTime=0;
            double totalReading=0.0;

            for (int i=1;i<readings.size ();i++){
                if (readings.get (i).getDate ()!=null){
                    Calendar calendar=Calendar.getInstance ();
                    calendar.setTime (readings.get (previousTime).getDate ());
                    Calendar calendar1=Calendar.getInstance ();
                    calendar1.setTime (readings.get (i).getDate ());
                    long difference=calendar1.getTimeInMillis ()-calendar.getTimeInMillis ();
                    double seconds=difference/1000.0;
                    double hours=seconds/(60.0*60.0);
                    double kw=readings.get (i).getReading ()+0.0;
                    Provider provider=providerRepository.findByName (smartMeter.getProviderId ());
                    totalReading+=(kw*hours)*provider.getAmountPerKwh ();
                    previousTime+=1;
                }else {
                    throw new RuntimeException("Not found");
                }
            }
            return totalReading;
        }catch (HttpClientErrorException.NotFound e){
            throw new Exception ("Smart meter not found");
        }

    }
}
