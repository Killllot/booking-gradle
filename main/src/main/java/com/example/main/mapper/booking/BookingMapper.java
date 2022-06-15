package com.example.main.mapper.booking;


import com.example.main.dto.booking.ValidatedBookingDto;
import com.example.main.dto.user.ValidatedUserDto;
import com.example.main.entity.BookingEntity;
import com.example.main.entity.RoomEntity;
import com.example.main.entity.UserEntity;
import com.example.main.models.Booking;

import java.util.HashMap;

public class BookingMapper {
    public static Booking toModel(BookingEntity book) {
        Booking booking = new Booking();
        booking.setId(book.getId());
        booking.setFromUtc(book.getFromUtc());
        booking.setToUtc(book.getToUtc());
        booking.setComment(book.getComment());
        var room = new HashMap<Long,String>();
        room.put(book.getRoom().getId(), book.getRoom().getName());
        booking.setRooms(room);

        return booking;
    }

    public static ValidatedUserDto toModel(UserEntity entity) {
        ValidatedUserDto model = new ValidatedUserDto();
        model.setId(entity.getId());
        model.setUserName(entity.getUsername());
        return model;
    }

    public static BookingEntity fromDtoToEntity(ValidatedBookingDto dto) {
        BookingEntity booking = new BookingEntity();
        booking.setId(dto.getId());
        booking.setFromUtc(dto.getFromUtc());
        booking.setToUtc(dto.getToUtc());
        booking.setComment(dto.getComment());
        booking.setUser(new UserEntity(dto.getUserId(), null));
        booking.setRoom(new RoomEntity(dto.getRoomId(),null));
        return booking;

    }
}
