package com.example.main.service.consumer;

import com.example.data.models.CurrencyRate;
import com.example.main.service.impl.KafkaConsumerServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    private static final String orderTopic = "${topicCurrency.name}";

    private final KafkaConsumerServiceImp kafkaConsumerService;

    @Autowired
    public Consumer(KafkaConsumerServiceImp kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("message consumed {}", message);

        CurrencyRate currency = objectMapper.readValue(message, CurrencyRate.class);
        kafkaConsumerService.persistFoodOrder(currency);
    }
}
