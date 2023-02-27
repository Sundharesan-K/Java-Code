package com.trustrace.security30.service;

import com.trustrace.security30.dto.APIResponse;
import com.trustrace.security30.pojo.Provider;
import com.trustrace.security30.pojo.Readings;
import com.trustrace.security30.pojo.SmartMeter;
import com.trustrace.security30.repository.ProviderRepository;
import com.trustrace.security30.repository.SmartMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Calendar;
import java.util.List;

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

    public String calculate(String meterId) throws Exception{
        try{
            SmartMeter smartMeter=meterRepository.findByMeterId (meterId);
            List<Readings> readings=smartMeter.getReadings ();
            int previousTime=0;
            double totalReading=0.0;

            for(int i=1;i<readings.size ();i++){
                if (readings.get (i).getData ()!=null){
                    Calendar calendar=Calendar.getInstance ();
                    calendar.setTime (readings.get (previousTime).getData ());
                    Calendar calendar1=Calendar.getInstance ();
                    calendar1.setTime (readings.get (i).getData ());
                    long difference=calendar1.getTimeInMillis ()-calendar.getTimeInMillis ();
                    double seconds=difference/1000.0;
                    double hours=seconds/(60.0*60.0);
                    double kw=readings.get (i).getReadings ()+0.0;
                    Provider provider=providerRepository.findByName (smartMeter.getProviderId ());
                    totalReading+=(kw*hours)*provider.getAmountPerKwh ();
                }else {
                    throw new Exception ("Value not found");
                }
            }
            return totalReading + "";
        }catch (HttpClientErrorException.NotFound e){
            throw new Exception ("SmartMeter not found");
        }
    }
}
