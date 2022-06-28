package com.example.data.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
public class ValidatedUserDto {

    private Long id;
    @NotNull
    @Size(min = 3, message = "Имя пользователя должно быть более 3-x символов")
    private String userName;
    @Email
    private String email;
    @NotNull
    private String password;

}
