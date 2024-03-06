package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.SmartMeterDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.Status;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.SmartMeter;

import java.util.List;

public interface SmartMeterService {

    String addSmartMeter(SmartMeterDto smartMeterDto) throws Exception;

    void smartMeterReadings(String meterId, ReadingDto readingDto) throws Exception;

    Double calculate(String meterId) throws Exception;

    String changeMeterStatus(String meterId, Status updateStatus) throws Exception;

    List<SmartMeter> getAllSmartMeters();
}
