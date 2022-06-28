package com.example.main.service.Interface;

import com.example.main.entity.RoomEntity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface RoomService {
    RoomEntity createRoom(RoomEntity room);

    List<RoomEntity> getUnoccupiedRooms (LocalDateTime FromUtc, LocalDateTime ToUtc);

    List<RoomEntity> getAll ();
}
