package com.example.main.service.producer;

import com.example.data.models.Booking;
import com.example.main.controllers.kafka.KafkaTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProducerBooking {
    @Value("${topic.name}")
    private String orderTopic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public ProducerBooking(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(KafkaTest test) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String orderAsMessage = objectMapper.writeValueAsString(test);
        kafkaTemplate.send(orderTopic, orderAsMessage);

        log.info("message produced {}", orderAsMessage);

        return "message sent";
    }
}
