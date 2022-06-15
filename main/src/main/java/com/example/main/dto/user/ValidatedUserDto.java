package com.example.main.dto.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ValidatedUserDto {

    private Long id;
    @NotNull
    @Size(min = 3, message = "Имя пользователя должно быть более 3-x символов")
    private String userName;
    private String email;
    @NotNull
    private String password;



    public ValidatedUserDto() {
    }
}
