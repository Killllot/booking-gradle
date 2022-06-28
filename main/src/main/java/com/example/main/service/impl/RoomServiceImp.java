package com.example.main.service.impl;


import com.example.main.entity.RoomEntity;
import com.example.main.repository.BookingRepository;
import com.example.main.repository.RoomRepository;
import com.example.main.service.Interface.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Service
public class RoomServiceImp implements RoomService {

    @Value("${const.minimumBookingDuration}")
    private long minimumBookingDuration;

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImp(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomEntity createRoom(RoomEntity room) {
        if(roomRepository.findByName(room.getName()).orElse(null)!=null){
            throw new RuntimeException("There is already a room with this name");
        }
        return roomRepository.save(room);
    }

    @Override
    public List<RoomEntity> getUnoccupiedRooms (LocalDateTime FromUtc, LocalDateTime ToUtc) {
        if( ChronoUnit.MINUTES.between(FromUtc, ToUtc) <minimumBookingDuration) {
            throw new RuntimeException("The booking time cannot be negative and must be more than "+ minimumBookingDuration +" minutes");
        }
        List<RoomEntity> list = roomRepository.findRoomEntityByBookingsBetween(FromUtc,ToUtc).orElse(null);
        return list;
    }

    @Override
    public List<RoomEntity> getAll () {
        return roomRepository.findAll();
    }
}
