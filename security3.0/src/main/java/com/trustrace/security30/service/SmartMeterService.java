package com.trustrace.security30.service;

import com.trustrace.security30.pojo.Provider;
import com.trustrace.security30.pojo.Readings;
import com.trustrace.security30.pojo.SmartMeter;
import com.trustrace.security30.repository.ProviderRepository;
import com.trustrace.security30.repository.SmartMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Calendar;


@Service
public class SmartMeterService {
    @Autowired
    private SmartMeterRepository meterRepository;
    @Autowired
    private ProviderRepository providerRepository;


    public String createSmartMeter(SmartMeter smartMeter) {
        SmartMeter smartMeter1=meterRepository.findByMeterId(smartMeter.getMeterId ());
        if (smartMeter1 !=null){
            throw new RuntimeException ("SmartMeter Already Exists");
        }
        smartMeter.setStatus ("pending Approval");
        return meterRepository.save (smartMeter);
    }

    public String getAllSmartMeters() {
        meterRepository.getAllSmartMeters();
        return "Success";
    }


}
