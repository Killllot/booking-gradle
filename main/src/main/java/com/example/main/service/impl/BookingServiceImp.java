package com.example.main.service.impl;


import com.example.main.entity.BookingEntity;
import com.example.main.entity.RoomEntity;
import com.example.main.entity.UserEntity;
import com.example.main.repository.BookingRepository;
import com.example.main.repository.RoomRepository;
import com.example.main.repository.UserRepository;
import com.example.main.service.Interface.BookingService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingServiceImp implements BookingService {

    @Value("${const.minimumBookingDuration}")
    private long minimumBookingDuration;

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingServiceImp(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public BookingEntity createBooking(BookingEntity booking)  {

        UserEntity user = userRepository.findById(booking.getUser().getId()).orElse(null);
        if(user==null) {
            throw  new RuntimeException("User with id:{" + booking.getUser().getId() +"} not found");
        }

        RoomEntity room = roomRepository.findById(booking.getRoom().getId()).orElse(null);
        if (room==null) {
            throw new RuntimeException("oom with id:{" + booking.getRoom().getId() +"} not found");
        }

        if( ChronoUnit.MINUTES.between(booking.getFromUtc(), booking.getToUtc()) < minimumBookingDuration) {
            throw new RuntimeException("The booking time cannot be negative and must be more than "+ minimumBookingDuration +" minutes");
        }

        var newAlreadyCreatedBooking = bookingRepository.findBookingEntityByFromUtcIsBeforeAndToUtcAfter(booking.getFromUtc(),booking.getToUtc(),booking.getRoom().getId());
        if(newAlreadyCreatedBooking.size()!=0) {
            throw new RuntimeException("A booking with such a date already exists");
        }

        booking.setUser(user);
        booking.setRoom(room);

        return bookingRepository.save(booking);
    }

    @Override
    public BookingEntity getBooking(Long id) {
        BookingEntity booking = bookingRepository.findById(id).orElse(null);
        if(booking==null) {
            throw new RuntimeException("Booking with id:{" + id +"} not found");
        }
        return booking;
    }


    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @Override
    public Page<BookingEntity> getBookingByPage(Integer quantity, Integer page,String[] sort) {
        List<Sort.Order> orders = new ArrayList<Sort.Order>();

        if (sort[0].contains(",")) {
            // will sort more than 2 fields
            // sortOrder="field, direction"
            for (String sortOrder : sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            // sort=[field, direction]
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        Pageable paging = PageRequest.of(page,quantity,Sort.by(orders));

        return bookingRepository.findAll(paging);
    }

    @Override
    public List<BookingEntity> getAll() {
        return bookingRepository.findAll();
    }
}


