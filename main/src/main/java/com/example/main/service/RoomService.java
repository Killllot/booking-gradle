package com.example.main.service;


import com.example.main.entity.RoomEntity;
import com.example.main.repository.BookingRepository;
import com.example.main.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class RoomService {

    @Value("${const.minimumBookingDuration}")
    private long minimumBookingDuration;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public RoomEntity createRoom(RoomEntity room) {
        if(roomRepository.findByName(room.getName()).orElse(null)!=null){
            throw new RuntimeException("Комната с таким названием уже есть");
        }
        return roomRepository.save(room);
    }

    public List<RoomEntity> getUnoccupiedRooms (LocalDateTime FromUtc, LocalDateTime ToUtc) {
        if( ChronoUnit.MINUTES.between(FromUtc, ToUtc) <minimumBookingDuration) {
            throw new RuntimeException("Время бронирования не может быть отрицательным и должно быть больше "+ minimumBookingDuration +" минут");
        }
        List<RoomEntity> list = roomRepository.findRoomEntityByBookingsBetween(FromUtc,ToUtc).orElse(null);
        return list;
    }

    public List<RoomEntity> getAll () {
        return roomRepository.findAll();
    }
}
