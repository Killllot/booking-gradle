package com.example.main.service.impl;

import com.example.data.models.CurrencyRate;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerServiceImp {
    private final ModelMapper modelMapper;

    @Autowired
    public KafkaConsumerServiceImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CurrencyRate persistFoodOrder(CurrencyRate currency) {
        return currency;
    }

}
