package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ReadingDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.SmartMeter;

import java.util.List;

public interface SmartMeterDao {
    SmartMeter findSmartMeter(String meterId);

    void addSmartMeter(SmartMeter smartMeter);

    void updateSmartMeterReadings(String meterId, ReadingDto readingDto);

    void updateStatus(String meterId, String status);

    List<SmartMeter> getAllSmartmeter();
}
