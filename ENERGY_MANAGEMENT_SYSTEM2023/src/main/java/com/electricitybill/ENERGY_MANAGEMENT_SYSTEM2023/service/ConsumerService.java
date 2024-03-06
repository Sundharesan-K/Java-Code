package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ConsumerDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Consumer;

import java.util.List;

public interface ConsumerService {
    void createConsumer(ConsumerDto consumerDto) throws Exception;

    Consumer login(ConsumerDto consumerDto) throws Exception;

    List<Consumer> getAllConsumer();

    Consumer getConsumer(String id);
}
