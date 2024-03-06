package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.AdminDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.ConsumerDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.SmartMeterDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.SmartMeterDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.Status;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Consumer;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Provider;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.SmartMeter;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.SmartMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SmartMeterServiceImpl implements SmartMeterService {
    private final SmartMeterDao smartMeterDao;
    private final AdminDao adminDao;
    private final ConsumerDao consumerDao;

    @Override
    public String addSmartMeter(SmartMeterDto smartMeterDto) throws Exception {
        try {
            if (smartMeterDao.findSmartMeter(smartMeterDto.getMeterId()) == null) {
                if (Objects.nonNull(consumerDao.findConsumer(smartMeterDto.getUsername()))) {
                    Random random = new Random();
                    if (Objects.nonNull(adminDao.findProvider(smartMeterDto.getProvider()))) {
                        List<ReadingDto> readings = Collections.EMPTY_LIST;
                        SmartMeter smartMeter = new SmartMeter();
                        smartMeter.setMeterId(String.valueOf(random.nextInt(999999)));
                        smartMeter.setUsername(smartMeterDto.getUsername());
                        smartMeter.setProvider(smartMeterDto.getProvider());
                        smartMeter.setReadings(readings);
                        smartMeter.setStatus("NEWLY_ADDED");
                        smartMeterDao.addSmartMeter(smartMeter);
                        return "Smart meter added successfully";
                    } else {
                        throw new Exception("Provider does not exists");
                    }
                } else {
                    throw new Exception("Username does not exists");
                }

            } else {
                throw new Exception("SmartMeter already exists");
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public void smartMeterReadings(String meterId, ReadingDto readingDto) throws Exception {
        try {
            if (Objects.nonNull(smartMeterDao.findSmartMeter(meterId))) {
                readingDto.setKw(10);
                readingDto.setDate(new Date().toString());
                readingDto.setTime(LocalTime.now().toString());
                smartMeterDao.updateSmartMeterReadings(meterId, readingDto);
            } else {
                throw new Exception("Smart meter not found");
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public String changeMeterStatus(String meterId, Status updateStatus) throws Exception {
        try {
            SmartMeter smartMeter = smartMeterDao.findSmartMeter(meterId);
            if (smartMeter.getStatus().equals("NEWLY_ADDED") && updateStatus.getNewMeter()) {
                smartMeterDao.updateStatus(smartMeter.getMeterId(), updateStatus.getStatus());
                Consumer consumer = consumerDao.findConsumer(smartMeter.getUsername());
                if (updateStatus.getStatus().equals("APPROVED")) {
                    consumer.getMeterId().add(meterId);
                    consumerDao.updateSmartMeterForConsumer(consumer.getId(), consumer.getMeterId());
                    smartMeterDao.updateStatus(smartMeter.getMeterId(), "APPROVED");
                    return "Smart meter approved";
                }
                smartMeterDao.updateStatus(smartMeter.getMeterId(), "REJECTED");
                throw new Exception("Smart meter rejected");

            } else if (smartMeter.getStatus().equals("NEWLY_ADDED") && !updateStatus.getNewMeter()) {
                throw new Exception("Smart meter not installed for enable/disable");
            } else if (!smartMeter.getStatus().equals("NEWLY_ADDED") && updateStatus.getNewMeter()) {
                throw new Exception("Smart meter already exist");
            } else {
                smartMeterDao.updateStatus(smartMeter.getMeterId(), updateStatus.getStatus());
                return updateStatus + "successfully";
            }
        } catch (Exception e) {
            throw new Exception("Smart meter not found");
        }

    }

    @Override
    public List<SmartMeter> getAllSmartMeters() {
        return smartMeterDao.getAllSmartmeter();
    }

    @Override
    public Double calculate(String meterId) throws Exception {
        try {
            SmartMeter smartMeter = smartMeterDao.findSmartMeter(meterId);
            List<ReadingDto> readings = smartMeter.getReadings();
            Integer previousTime = 0;
            Double totalReading = 0.0;
            for (int i = 1; i < readings.size(); i++) {
                if (readings.get(i).getTime() != null) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    Date date1 = format.parse(readings.get(previousTime).getTime());
                    Date date2 = format.parse(readings.get(i).getTime());
                    long difference = date2.getTime() - date1.getTime();
                    double seconds = difference / 1000.0;
                    double hours = seconds / (60.0 * 60.0);
                    Double kw = readings.get(i).getKw() + 0.0;
                    Provider provider = adminDao.findProvider(readings.get(i).getProvider());
                    totalReading += (kw * hours) * provider.getRate();
                    previousTime += 1;
                } else {
                    throw new Exception("Value not found");
                }
            }
            return totalReading;
        } catch (Exception e) {
            throw new Exception("smart meter not found");
        }
    }
}
