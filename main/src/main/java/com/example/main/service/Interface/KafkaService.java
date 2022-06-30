package com.example.main.service.Interface;

import com.example.main.controllers.kafka.KafkaTest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface KafkaService {
    String sendMessage(KafkaTest test) throws JsonProcessingException;
}
