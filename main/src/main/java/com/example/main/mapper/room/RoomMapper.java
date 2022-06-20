package com.example.main.mapper.room;

//import com.newBooking.DTO.Room.createRoomDTO;
//import com.newBooking.Data.Entity.RoomEntity;

import com.example.data.dto.room.ValidatedRoomDto;
import com.example.main.entity.RoomEntity;
import com.example.data.models.Room;

public class RoomMapper {
    public static Room toModel (RoomEntity roomEntity) {
        Room room = new Room();
        room.setId(roomEntity.getId());
        room.setName(roomEntity.getName());

        return room;
    }
    public static RoomEntity fromDtoToEntity (ValidatedRoomDto roomEntity) {
        RoomEntity room = new RoomEntity();
        room.setId(roomEntity.getId());
        room.setName(roomEntity.getName());

        return room;
    }
    public static ValidatedRoomDto toDto (ValidatedRoomDto roomEntity) {
        ValidatedRoomDto room = new ValidatedRoomDto();
        room.setId(room.getId());
        room.setName(room.getName());

        return room;
    }
}
