package com.example.data.dto.room;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class ValidatedRoomDto {

    private Long id;
    @Size(min = 2)
    private String name;

    public ValidatedRoomDto(Long id, String name) {
        setId(id);
        setName(name);
    }

    public ValidatedRoomDto() {

    }


}
