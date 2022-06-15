package com.example.main.dto.room;

import lombok.Data;


@Data
public class ValidatedRoomDto {

    private Long id;
    private String name;

    public ValidatedRoomDto(Long id, String name) {
        setId(id);
        setName(name);
    }

    public ValidatedRoomDto() {

    }


}
