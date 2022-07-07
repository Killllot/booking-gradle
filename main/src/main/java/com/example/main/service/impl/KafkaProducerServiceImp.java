package com.example.main.service.impl;

import com.example.main.controllers.kafka.KafkaTest;
import com.example.main.service.Interface.KafkaService;
import com.example.main.service.producer.ProducerBooking;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("kafkaBooking")
public class KafkaProducerServiceImp implements KafkaService {
    private final ProducerBooking producer;

    @Autowired
    public KafkaProducerServiceImp(ProducerBooking producer) {
        this.producer = producer;
    }

    @Override
    public String sendMessage(KafkaTest test) throws JsonProcessingException {
        return producer.sendMessage(test);
    }
}
