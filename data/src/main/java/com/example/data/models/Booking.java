package com.example.data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    private Long id;
    private LocalDateTime fromUtc;
    private LocalDateTime toUtc;
    private String Comment;
    private Map<Long,String> rooms;

}
