package com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.impl;

import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dao.ConsumerDao;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.dto.ConsumerDto;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.entity.Consumer;
import com.electricitybill.ENERGY_MANAGEMENT_SYSTEM2023.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerDao consumerDao;

    @Override
    public void createConsumer(ConsumerDto consumerDto) throws Exception {
        try {
            if (consumerDao.findConsumer(consumerDto.getUsername()) == null) {
                Consumer consumer = new Consumer();
                List<String> smartMeter = Collections.emptyList();
                consumer.setUsername(consumerDto.getUsername());
                consumer.setFirstName(consumerDto.getFirstName());
                consumer.setLastName(consumerDto.getLastName());
                consumer.setMeterId(smartMeter);
                consumerDao.addConsumer(consumer);
            } else {
                throw new Exception("Consumer already exists");
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public Consumer login(ConsumerDto consumerDto) throws Exception {
        Consumer consumer = consumerDao.findConsumer(consumerDto.getUsername());
        try {
            if (Objects.nonNull(consumer)) {
                return consumer;
            } else {
                throw new Exception("Username is incorrect");
            }
        } catch (Exception e) {
            throw new Exception("Internal server error");
        }
    }

    @Override
    public List<Consumer> getAllConsumer() {
        return consumerDao.findAllConsumer();
    }

    @Override
    public Consumer getConsumer(String id) {
        try {
            return consumerDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Consumer not found");
        }
    }
}
