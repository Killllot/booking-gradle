package com.example.main.repository;


import com.example.main.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    Optional<RoomEntity> findByName(String name);
    @Query(
            value = "SELECT DISTINCT r.id, r.name FROM rooms r " +
                    "LEFT JOIN bookings b ON r.id = b.room_id " +
                    "WHERE " +
                    "r.id NOT IN (SELECT bookings.room_id FROM bookings " +
                    "WHERE from_utc BETWEEN ?1 AND ?2 OR to_utc BETWEEN ?1 AND ?2 )" +
                    "OR from_utc IS NULL " +
                    "ORDER BY r.id ",
            nativeQuery = true
    )
    Optional<List<RoomEntity>> findRoomEntityByBookingsBetween(LocalDateTime fromUtc,LocalDateTime toUtc);

}
