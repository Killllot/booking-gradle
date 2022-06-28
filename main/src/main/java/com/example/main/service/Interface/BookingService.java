package com.example.main.service.Interface;

import com.example.main.entity.BookingEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookingService {
    BookingEntity createBooking(BookingEntity booking);

    BookingEntity getBooking(Long id);

    Page<BookingEntity> getBookingByPage(Integer quantity, Integer page, String[] sort);

    List<BookingEntity> getAll();
}
