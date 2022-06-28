package com.example.main.controllers;


import com.example.data.dto.booking.ValidatedBookingDto;
import com.example.main.mapper.booking.BookingMapper;
import com.example.data.models.Booking;
import com.example.main.service.Interface.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@Validated
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity createBooking(@Valid  @RequestBody ValidatedBookingDto book) {
            return ResponseEntity
                    .ok(BookingMapper.toModel(bookingService.createBooking(BookingMapper.fromDtoToEntity(book))));
    }

    @GetMapping("/get")
    public ResponseEntity<Booking> getBooking (@RequestParam Long bookingId) {
        return new ResponseEntity<>(BookingMapper.toModel(bookingService.getBooking(bookingId)), HttpStatus.FOUND);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Booking>> getAll() {
        return new ResponseEntity<>(bookingService.getAll().stream()
                .map(BookingMapper::toModel)
                .collect(Collectors.toList()),HttpStatus.FOUND);
    }

    @GetMapping("/getByPage")
    public ResponseEntity<?> getBookingByPage (@RequestParam(required = false) String sorting,
                                               @RequestParam(defaultValue = "5") Integer quantity,
                                               @RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "id,desc") String[] sort) {

        return new ResponseEntity<>(bookingService.getBookingByPage(quantity,page,sort).stream()
                .map(BookingMapper::toModel)
                .collect(Collectors.toList()), HttpStatus.FOUND);
    }

}
