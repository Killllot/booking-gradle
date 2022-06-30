package com.example.main.controllers.kafka;

import com.example.data.models.Booking;
import com.example.main.service.Interface.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/kafka")
public class BookingKafkaController {
    private final KafkaService kafkaService;

    @Autowired
    public BookingKafkaController(KafkaService kafkaService) {
        this.kafkaService = kafkaService;
    }

    @PostMapping
    public String createBooking(@RequestBody KafkaTest test) throws JsonProcessingException {
        log.info("create booking request received");
        return kafkaService.sendMessage(test);
    }
}
