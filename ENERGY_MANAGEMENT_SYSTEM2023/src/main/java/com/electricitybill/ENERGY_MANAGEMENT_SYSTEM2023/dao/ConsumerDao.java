package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Consumer;

import java.util.List;

public interface ConsumerDao {
    Consumer findConsumer(String username);

    void addConsumer(Consumer consumer);

    List<Consumer> findAllConsumer();

    Consumer findById(String id);

    void updateSmartMeterForConsumer(String id, List<String> meterId);
}
