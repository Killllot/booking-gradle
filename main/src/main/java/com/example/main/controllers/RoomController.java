package com.example.main.controllers;

import com.example.main.dto.room.ValidatedRoomDto;
import com.example.main.mapper.room.RoomMapper;
import com.example.main.models.Room;
import com.example.main.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@Validated
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity createRoom(@Valid @RequestBody ValidatedRoomDto room) {

        return ResponseEntity.ok(RoomMapper.toModel(roomService.createRoom(RoomMapper.fromDtoToEntity(room))));
    }

    @GetMapping("/getUnoccupiedRooms")
    public ResponseEntity<List<Room>> getUnoccupiedRooms(@NotNull @RequestParam("FromUtc")
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                                 LocalDateTime FromUtc,
                                                         @NotNull @RequestParam("ToUtc")
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
                                                                 LocalDateTime ToUtc) {

        return ResponseEntity.ok(roomService.getUnoccupiedRooms(FromUtc, ToUtc).stream()
                    .map(value -> new Room(value.getId(), value.getName()))
                    .collect(Collectors.toList()));

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Room>> getAll() {
        return ResponseEntity.ok(roomService.getAll().stream()
                .map(value -> new Room(value.getId(), value.getName()))
                .collect(Collectors.toList()));
    }

}
